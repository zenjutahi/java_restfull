package com.learning.rest.webservice.restfullwebservices.usertest;

import com.learning.rest.webservice.restfullwebservices.posts.PostRepository;
import com.learning.rest.webservice.restfullwebservices.user.User;
import com.learning.rest.webservice.restfullwebservices.user.UserJPAResource;
import com.learning.rest.webservice.restfullwebservices.user.UserRepository;
import com.learning.rest.webservice.restfullwebservices.user.UserResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserJPAResource.class)
public class UserControllerTest2 {

    private User user;

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PostRepository postRepository;

    private JacksonTester<User> jsonUser;

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

        when(userRepository.findAll()).thenReturn(
                Arrays.asList(user)
        );

        // call "/users" format application/json
        RequestBuilder request = MockMvcRequestBuilders
                .get("/jpa/users")
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

        when(userRepository.findById(1001)).thenReturn(Optional.of(user));

        // call "/users" format application/json
        RequestBuilder request = MockMvcRequestBuilders
                .get("/users/{id}", 1)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
//    @Test
//    public void createUserTest() throws Exception {
//
//    }

}



