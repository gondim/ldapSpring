package com.atlantico.prova.api.resource;

import java.util.List;

import javax.naming.Name;
import javax.print.attribute.standard.Severity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atlantico.prova.api.model.User;
import com.atlantico.prova.api.repository.UserRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/Users")
public class UserResource {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public List<User> list() {
		return (List<User>) userRepository.findAll();
	}
	
	@GetMapping("/{uid}")
	public ResponseEntity<?> getbyUid(
			@ApiParam(value = "Uid from ldap", example = "marry")
			@PathVariable String uid) {
		User user = userRepository.findByUid(uid);
		return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> create(
			@ApiParam(value = "data to Create ldap")
			@RequestBody User user) {
		Name dn = LdapNameBuilder
		          .newInstance()
		          .add("ou", "users")
		          .add("cn", user.getCn())
		          .build();
		user.setId(dn);
		user.setNewLdap(true);
		User savedUser = userRepository.save(user);	
		return ResponseEntity.created(null).body(savedUser);
	}
	
	@DeleteMapping("/{uid}")
	public ResponseEntity<?> delete(@PathVariable String uid) {
		User user = userRepository.findByUid(uid);
		if(user != null) userRepository.delete(user);
		return ResponseEntity.ok().build();
	}

}
