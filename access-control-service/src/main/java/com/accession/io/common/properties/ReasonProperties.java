package com.accession.io.common.properties;

import com.accession.io.message.models.ReasonModel;
import jakarta.annotation.PostConstruct;
import lombok.Data;
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
public class ReasonProperties {

    private Map<String, ReasonModel> reasons;

    private static ReasonProperties instance;

    public static ReasonModel of(String key) {
        return instance.reasons.get(key);
    }

    @PostConstruct
    public void init() {
        instance = this;
    }
}
