package com.accession.io.common.interceptors;

import com.accession.io.application.services.UserService;
import com.accession.io.common.contexts.UserContext;
import com.accession.io.domain.entities.User;
import com.accession.io.message.models.UserModel;
import io.sentry.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Collections;
import java.util.List;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-16 14:16:41
 */
@Component
@RequiredArgsConstructor
public class UserIdentityInterceptor implements HandlerInterceptor {

    @Value("${authorization.headers.user-name}")
    private String userIdentityHeader;

    private final UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String userId = request.getHeader(this.userIdentityHeader);
        if (!ObjectUtils.isEmpty(userId)) {
            User user = userService.findById(userId);
            setUserContext(user);
            setSentryContext(user);
        }
        setB3Propagation(request);
        return true;
    }

    private void setUserContext(User user) {
        UserModel userModel = new UserModel(user.getId().getId(), user.getEmail().getEmail());
        UserContext.set(userModel);
    }

    private void setSentryContext(User user) {
        io.sentry.protocol.User sentryUser = new io.sentry.protocol.User();
        sentryUser.setId(user.getId().getId());
        sentryUser.setEmail(user.getEmail().getEmail());
        Sentry.configureScope(scope -> scope.setUser(sentryUser));
    }

    private void setB3Propagation(HttpServletRequest request) {
        String sentryTraceHeader = request.getHeader(SentryTraceHeader.SENTRY_TRACE_HEADER);
        List<String> baggageHeader = Collections.list(request.getHeaders(BaggageHeader.BAGGAGE_HEADER));
        TransactionContext transactionContext = Sentry.continueTrace(sentryTraceHeader, baggageHeader);
        if (transactionContext != null) Sentry.startTransaction(transactionContext);
    }
}
