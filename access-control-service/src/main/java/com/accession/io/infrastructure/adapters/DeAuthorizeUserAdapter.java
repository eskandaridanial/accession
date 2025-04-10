package com.accession.io.infrastructure.adapters;

import com.accession.io.common.contracts.interfaces.BaseAdapter;
import com.accession.io.common.contracts.records.BaseResponse;
import com.accession.io.message.commands.DeAuthorizeUserCommand;
import com.accession.io.message.responses.AuthorizeUserResponse;
import com.accession.io.message.responses.DeAuthorizeUserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-13 13:28:04
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DeAuthorizeUserAdapter implements BaseAdapter<DeAuthorizeUserCommand, DeAuthorizeUserResponse> {

    @Value("${de-authorization.url}")
    private String deAuthorizationUrl;

    private final RestTemplate restTemplate;

    @Override
    public BaseResponse<DeAuthorizeUserResponse> execute(DeAuthorizeUserCommand command) {
        ResponseEntity<BaseResponse<DeAuthorizeUserResponse>> responseEntity = restTemplate.exchange(
                this.deAuthorizationUrl, HttpMethod.POST, new HttpEntity<>(command), new ParameterizedTypeReference<>() {});
        return responseEntity.getBody();
    }
}
