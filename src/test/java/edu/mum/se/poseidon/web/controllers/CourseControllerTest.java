package edu.mum.se.poseidon.web.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import edu.mum.se.poseidon.web.Main;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Main.class)
@WebAppConfiguration
public class CourseControllerTest {

	private MockMvc mockMvc;

    @Autowired
    WebApplicationContext wac;
    
    @Autowired
	public UserDetailsService userDetailsService;
    
    @Before
    public void setup() throws Exception{
    	
        // Process mock annotations
        MockitoAnnotations.initMocks(this);
        
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    @WithMockUser(roles={"ADMIN"})
    public void testListCourses() throws Exception{
        this.mockMvc.perform(get("/admin/course"))
        						.andExpect(status().isOk());  
    }
}
