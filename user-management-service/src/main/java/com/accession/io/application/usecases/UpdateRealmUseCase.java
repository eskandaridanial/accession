package com.accession.io.application.usecases;

import com.accession.io.application.services.RealmService;
import com.accession.io.application.services.RoleService;
import com.accession.io.common.contracts.interfaces.BaseUseCase;
import com.accession.io.domain.entities.Realm;
import com.accession.io.domain.values.Name;
import com.accession.io.message.commands.UpdateRealmCommand;
import com.accession.io.message.responses.UpdateRealmResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 13:27:45
 */
@Component
@RequiredArgsConstructor
public class UpdateRealmUseCase implements BaseUseCase<UpdateRealmCommand, UpdateRealmResponse> {

    private final RoleService roleService;
    private final RealmService realmService;

    @Override
    public UpdateRealmResponse execute(UpdateRealmCommand command) {
        Realm realm = realmService.findById(command.id());

        var finalRealm = realm;
        command.name().ifPresent(name -> finalRealm.setName(Name.of(name)));
        command.roleIds().ifPresent(roleIds -> finalRealm.setRoles(roleService.findAllByIds(roleIds)));

        realm = realmService.save(finalRealm);

        return UpdateRealmResponse.of(realm);
    }
}
