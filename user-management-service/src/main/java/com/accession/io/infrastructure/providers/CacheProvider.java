package com.accession.io.infrastructure.providers;

import io.sentry.spring.jakarta.tracing.SentrySpan;

import java.util.Date;

/**
* @author: Danial Eskandari
* @createdAt: 2024-11-11 13:26:32
*/
@SentrySpan(description = "CacheProvider")
public interface CacheProvider {

    Boolean put(String key, Object value, Date expiresAt);

    Boolean exists(String key);
}
