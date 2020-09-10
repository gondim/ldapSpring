package com.atlantico.prova.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;

import com.atlantico.prova.api.model.User;

public class LdapClient{

    @Autowired
    private LdapTemplate ldapTemplate;


    public String create(User user) {
        ldapTemplate.create(user);
		return "ok";
    }

}
