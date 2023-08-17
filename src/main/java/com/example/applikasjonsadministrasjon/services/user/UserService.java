package com.example.applikasjonsadministrasjon.services.user;

import com.example.applikasjonsadministrasjon.models.tables.Stilling;
import com.example.applikasjonsadministrasjon.models.tables.User;
import com.example.applikasjonsadministrasjon.services.CrudService;

import java.util.Set;

public interface UserService extends CrudService <User, String> {

    Set<Stilling> findAllStilling(String id);
}
