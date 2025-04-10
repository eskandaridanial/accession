package com.accession.io.application.services.implementations;

import com.accession.io.application.services.RealmService;
import com.accession.io.domain.entities.Realm;
import com.accession.io.domain.values.UniqueId;
import com.accession.io.exception.AlreadyExistsException;
import com.accession.io.exception.RecordNotFoundException;
import com.accession.io.exception.reasons.AlreadyExistsReason;
import com.accession.io.exception.reasons.RecordNotFoundReason;
import com.accession.io.infrastructure.repositories.RealmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-06 13:08:13
 */
@Component
@RequiredArgsConstructor
public class RealmServiceImpl implements RealmService {

    private final RealmRepository realmRepository;

    @Override
    public Realm save(Realm realm) {
        try {
            return realmRepository.save(realm);
        } catch (DataIntegrityViolationException ex) {
            throw new AlreadyExistsException(AlreadyExistsReason.realmAlreadyExists());
        }
    }

    @Override
    public Realm findById(String realmId) {
        return realmRepository.findById(UniqueId.of(realmId))
                .orElseThrow(() -> new RecordNotFoundException(RecordNotFoundReason.realmNotFoundById()));
    }

    @Override
    public List<Realm> findAll() {
        return realmRepository.findAll();
    }
}
