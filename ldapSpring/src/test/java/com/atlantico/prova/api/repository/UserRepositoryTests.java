package com.atlantico.prova.api.repository;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.naming.Name;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import com.atlantico.prova.api.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {
    
    @Autowired
    private UserRepository userRepository;
    
    public final String cn = "cnTest";
    public final String sn = "snTest";
    public final String uid = "uidTest";

    @Before
	public void setUp() throws Exception {
    	User newUSer = new User();
		Name dn = LdapNameBuilder
		          .newInstance()
		          .add("ou", "users")
		          .add("cn", "cnTest")
		          .build();
		newUSer.setId(dn);
		newUSer.setSn("snTest");
		newUSer.setUid("uidTest");
		newUSer.setNewLdap(true);
		
		this.userRepository.save(newUSer);	
	}
    
    @After
    public final void tearDown() { 
    	User getUser = userRepository.findByUid(this.uid);
    	this.userRepository.delete(getUser);
	}
    
    @Test
    public void findLpadByUid() {
    	User getUser = userRepository.findByUid(this.uid);
    	assertEquals(this.uid, getUser.getUid());
    	assertEquals(this.cn, getUser.getCn());
    	assertEquals(this.sn, getUser.getSn());
    }
    
    @Test
    public void ListLpad() {
    	List<User> users = (List<User>) userRepository.findAll();
    	assertTrue(!users.isEmpty());
    }
}
