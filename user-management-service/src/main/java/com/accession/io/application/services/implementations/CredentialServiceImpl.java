package com.accession.io.application.services.implementations;

import com.accession.io.application.services.CredentialService;
import com.accession.io.domain.entities.BasicCredential;
import com.accession.io.domain.entities.common.BaseCredential;
import com.accession.io.domain.values.Password;
import com.accession.io.domain.values.Subject;
import com.accession.io.exception.RecordNotFoundException;
import com.accession.io.exception.reasons.RecordNotFoundReason;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-06 13:08:13
 */
@Component
public class CredentialServiceImpl implements CredentialService {

    @Override
    public BasicCredential createBasicCredential(String subject, String password) {
        BasicCredential credential = new BasicCredential();
        credential.setSubject(Subject.of(subject));
        credential.setPassword(Password.of(password));
        return credential;
    }

    @Override
    public <T extends BaseCredential> T findByType(List<BaseCredential> credentials, Class<T> credentialType) {
        return credentials.stream()
                .filter(credentialType::isInstance)
                .map(credentialType::cast)
                .findFirst()
                .orElseThrow(() -> new RecordNotFoundException(RecordNotFoundReason.credentialNotFoundByType()));
    }
}
