package com.accession.io.application.services;

import com.accession.io.domain.entities.common.BaseCredential;
import com.accession.io.domain.entities.BasicCredential;

import java.util.List;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-06 13:18:28
 */
public interface CredentialService {

     BasicCredential createBasicCredential(String subject, String password);

    <T extends BaseCredential> T findByType(List<BaseCredential> credentials, Class<T> credentialType);
}
