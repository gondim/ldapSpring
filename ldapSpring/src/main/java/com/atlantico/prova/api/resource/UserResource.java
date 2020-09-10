package com.atlantico.prova.api.resource;

import javax.naming.Name;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atlantico.prova.api.model.User;
import com.atlantico.prova.api.repository.UserRepository;

@RestController
@RequestMapping("/Users")
public class UserResource {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public Iterable<User> list() {
		return userRepository.findAll();
	}
	
	@GetMapping("/{uid}")
	public User getbyUid(@PathVariable String uid) {
		return userRepository.findByUid(uid);
	}
	
	
	@PostMapping
	public void create(@RequestBody User user) {
		Name dn = LdapNameBuilder
		          .newInstance()
		          .add("ou", "users")
		          .add("cn", user.getCn())
		          .build();
		user.setId(dn);
		user.setNewLdap(true);
		
		userRepository.save(user);	
	}
	
	@PutMapping("/{id}")
	public void update(@PathVariable String uid) {
		
	}
	
	@DeleteMapping("/{uid}")
	public void delete(@PathVariable String uid) {
		User user = userRepository.findByUid(uid);
		userRepository.delete(user);
	}

}
