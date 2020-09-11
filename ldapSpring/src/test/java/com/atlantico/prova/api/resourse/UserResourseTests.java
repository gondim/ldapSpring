package com.atlantico.prova.api.resourse;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.atlantico.prova.api.model.User;
import com.atlantico.prova.api.repository.UserRepository;
import com.atlantico.prova.api.resource.UserResource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(UserResource.class)
public class UserResourseTests {
	
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private UserRepository userRepository;
    
    @Test
	public void getAllUsers() throws Exception {
    	
    	User user1 = new User("Marry", "Doe", "marry");
    	User user2 = new User("Marcelo", "gondim", "marcelo");
    	User user3 = new User("Arthur", "Doe", "arthur");
    	
    	List<User> allUser = Arrays.asList(user1, user2, user3);
    	
    	given(userRepository.findAll())
    	.willReturn(allUser);
    	
		mvc.perform(MockMvcRequestBuilders.get("/Users")
		.accept(org.springframework.http.MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].cn", is("Marry")))
		.andExpect(jsonPath("$[1].cn", is("Marcelo")))
		.andExpect(jsonPath("$[2].cn", is("Arthur")));
	}
    
    @Test
	public void createUsers() throws Exception {
   
		given(userRepository.save(Mockito.any(User.class)))
		.willReturn(new User("Marry", "Doe", "marry"));
    	
		mvc.perform(MockMvcRequestBuilders.post("/Users")
		.content(this.createContentUser())
		.contentType(org.springframework.http.MediaType.APPLICATION_JSON)
		.accept(org.springframework.http.MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.cn", is("Marry")))
		.andExpect(jsonPath("$.sn", is("Doe")))
		.andExpect(jsonPath("$.uid", is("marry")));

	}
    
    @Test
	public void getUserByUid() throws Exception {
    	
		given(userRepository.findByUid(Mockito.anyString()))
		.willReturn(new User("Marry", "Doe", "marry"));
    	
		mvc.perform(MockMvcRequestBuilders.get("/Users/marry")
		.accept(org.springframework.http.MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.cn", is("Marry")))
		.andExpect(jsonPath("$.sn", is("Doe")))
		.andExpect(jsonPath("$.uid", is("marry")));
	}
    
    @Test
	public void deleteUserByUid() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete("/Users/testCreate")
		.accept(org.springframework.http.MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
    }
    
    private String createContentUser() throws JsonProcessingException {
    	User newUSer = new User("Marry", "Doe", "marry");
    	
    	ObjectMapper mapper = new ObjectMapper();
    	return mapper.writeValueAsString(newUSer);
    }
    
    

}
