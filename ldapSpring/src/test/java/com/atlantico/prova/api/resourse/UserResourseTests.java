package com.atlantico.prova.api.resourse;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.naming.Name;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.atlantico.prova.api.model.User;
import com.atlantico.prova.api.resource.UserResource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(UserResource.class)
public class UserResourseTests {
	
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private UserResource userResource;
	
    @Test
	public void getAllUsers() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/Users")
		.accept(org.springframework.http.MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().string("[]"));
	}
    
    @Test
	public void createUsers() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/Users")
		.content(this.createContentUser())
		.contentType(org.springframework.http.MediaType.APPLICATION_JSON)
		.accept(org.springframework.http.MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated());
	}
    
    @Test
	public void getUserByUid() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/Users/testeCreate")
		.accept(org.springframework.http.MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.cn", is("testeCreate")))
		.andExpect(jsonPath("$.sn", is("testeCreate")))
		.andExpect(jsonPath("$.uid", is("testeCreate")));
	}
    
    @Test
	public void deleteUserByUid() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete("/Users/testeCreate")
		.accept(org.springframework.http.MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
    }
    
    private String createContentUser() throws JsonProcessingException {
    	User newUSer = new User();
		newUSer.setCn("testeCreate");
		newUSer.setSn("testeCreate");
		newUSer.setUid("testeCreate");
		newUSer.setNewLdap(true);
    	
    	ObjectMapper mapper = new ObjectMapper();
    	return mapper.writeValueAsString(newUSer);
    }
    
    

}
