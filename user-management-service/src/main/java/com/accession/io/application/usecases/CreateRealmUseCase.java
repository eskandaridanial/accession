package com.accession.io.application.usecases;

import com.accession.io.application.services.RealmService;
import com.accession.io.application.services.RoleService;
import com.accession.io.common.contracts.interfaces.BaseUseCase;
import com.accession.io.domain.entities.Realm;
import com.accession.io.domain.entities.Role;
import com.accession.io.domain.values.Name;
import com.accession.io.message.commands.CreateRealmCommand;
import com.accession.io.message.responses.CreateRealmResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 13:27:45
 */
@Component
@RequiredArgsConstructor
public class CreateRealmUseCase implements BaseUseCase<CreateRealmCommand, CreateRealmResponse> {

    private final RoleService roleService;
    private final RealmService realmService;

    @Override
    public CreateRealmResponse execute(CreateRealmCommand command) {
        Realm realm = new Realm();
        realm.setName(Name.of(command.name()));
        List<Role> roles = roleService.findAllByIds(command.roleIds());
        realm.setRoles(roles);
        realm = realmService.save(realm);
        return CreateRealmResponse.of(realm);
    }
}
