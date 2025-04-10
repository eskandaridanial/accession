package com.accession.io.common.configurations;

import com.accession.io.common.interceptors.UserIdentityInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-16 14:28:00
 */
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    @Value("${server.path}")
    private String serverPath;

    private final UserIdentityInterceptor userIdentityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userIdentityInterceptor)
                .addPathPatterns(serverPath + "/**");
    }
}
