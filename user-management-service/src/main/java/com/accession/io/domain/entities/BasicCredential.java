package com.accession.io.domain.entities;

import com.accession.io.domain.entities.common.BaseCredential;
import com.accession.io.domain.values.Password;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-10 16:39:07
 */
@Data
@Entity
@Table(name = "basic_credentials")
@EqualsAndHashCode(callSuper = true)
public class BasicCredential extends BaseCredential {

    @Embedded
    private Password password;
}
