package com.accession.io.application.providers;

import com.accession.io.domain.entities.common.BaseCredential;
import com.accession.io.message.models.JwtModel;
import io.jsonwebtoken.Claims;

import java.util.Map;

/**
* @author: Danial Eskandari
* @createdAt: 2024-11-11 13:26:32
*/
public interface JwtProvider {

    Claims extract(String accessToken);

    JwtModel convert(String accessToken);

    <T extends BaseCredential> String issue(T credential, Map<String, Object> claims);
}
