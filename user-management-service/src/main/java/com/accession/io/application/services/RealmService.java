package com.accession.io.application.services;

import com.accession.io.domain.entities.Realm;

import java.util.List;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-06 13:18:28
 */
public interface RealmService {

    Realm save(Realm realm);

    Realm findById(String realmId);

    List<Realm> findAll();
}
