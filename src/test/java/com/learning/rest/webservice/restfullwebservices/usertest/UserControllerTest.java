package com.learning.rest.webservice.restfullwebservices.usertest;

import com.learning.rest.webservice.restfullwebservices.controller.ItemController;
import com.learning.rest.webservice.restfullwebservices.user.User;
import com.learning.rest.webservice.restfullwebservices.user.UserDAOService;
import com.learning.rest.webservice.restfullwebservices.user.UserJPAResource;
import com.learning.rest.webservice.restfullwebservices.user.UserResource;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserResource.class)
public class UserControllerTest {

    private User user;

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private UserDAOService userDAOService;

    private void setUpUser() {
        user = new User();
        user.setId(1001);
        user.setName("zenj");
        user.setBirthDate(new Date());
    }

    @BeforeEach
    public void setup() {
            setUpUser();
    }


    @Test
    public void userListTest() throws Exception {

        // mock userResource

        when(userDAOService.getUsers ()).thenReturn(
                Arrays.asList(user));

        // call "/users" format application/json
        RequestBuilder request = MockMvcRequestBuilders
                .get("/users")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"name\": \"zenj\"}]"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void getSingleUserTest() throws Exception {

        // mock userResource

        when(userDAOService.getOne(1)).thenReturn(user);

        // call "/users" format application/json
        RequestBuilder request = MockMvcRequestBuilders
                .get("/users/{id}", 1)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"name\": \"zenj\"}"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
    @Test
    public void createUserTest() throws Exception {
        when(userDAOService.save(user)).thenReturn(user);

        // call "/users" format application/json
        RequestBuilder request = MockMvcRequestBuilders
                .post("/users")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().json("[{\"name\": \"zenj\"}]"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

}



