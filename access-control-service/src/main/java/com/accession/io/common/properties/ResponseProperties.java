package com.accession.io.common.properties;

import com.accession.io.message.models.ResponseModel;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: Danial Eskandari
 * @createdAt: 2025-01-05 15:26:02
 */
@Data
@Component
@ConfigurationProperties
public class ResponseProperties {

    @Value("${response.success.query}")
    private String successQuery;

    private Map<String, ResponseModel> responses;

    private static ResponseProperties instance;

    public static ResponseModel of(String key) {
        return instance.responses.get(key);
    }

    public static ResponseModel of() {
        return instance.responses.get(instance.successQuery);
    }

    @PostConstruct
    public void init() {
        instance = this;
    }
}
