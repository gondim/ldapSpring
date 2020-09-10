package com.atlantico.prova.api.repository;

import org.springframework.data.ldap.repository.LdapRepository;

import com.atlantico.prova.api.model.User;

public interface UserRepository extends LdapRepository<User> {

	User findByUid(String uid);
}
