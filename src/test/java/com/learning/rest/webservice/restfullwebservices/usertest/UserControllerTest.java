package com.learning.rest.webservice.restfullwebservices.usertest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.rest.webservice.restfullwebservices.controller.ItemController;
//import com.learning.rest.webservice.restfullwebservices.support.UserBuilder;
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

//    UserBuilder builder = UserBuilder.user().id(1001);

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;


    @MockBean
    private UserDAOService userDAOService;

//    private void setUpUser() {
//        user = new User();
//        user.setId(1001);
//        user.setName("zenj");
//        user.setBirthDate(new Date());
//    }
    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    @BeforeEach
//    public void setup() {
//            setUpUser();
//    }


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
//    @Test
//    public void createUserTest() throws Exception {
//        when(userDAOService.save(user)).thenReturn(user);
//
//        // call "/users" format application/json
//        RequestBuilder request = MockMvcRequestBuilders
//                .post("/users")
//                .accept(MediaType.APPLICATION_JSON);
//
//        MvcResult mvcResult = mockMvc.perform(request)
//                .andExpect(status().isCreated())
//                .andExpect(content().json("[{\"name\": \"zenj\"}]"))
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//
//
//        builder.birthdate(new Date()).build();
//
//        mockMvc.perform( MockMvcRequestBuilders
//                        .post("/users")
//                        .content("{\"title\": \"h\",\"body\": \"Try book on Java spring Boot\"\n" +
//                                "}")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").exists());
//    }

}



