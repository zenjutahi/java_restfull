package com.learning.rest.webservice.restfullwebservices.usertest;

import com.learning.rest.webservice.restfullwebservices.controller.ItemController;
import com.learning.rest.webservice.restfullwebservices.user.UserJPAResource;
import com.learning.rest.webservice.restfullwebservices.user.UserResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserResource.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void userList_basic() throws Exception {
        // call "/users" format application/json
        RequestBuilder request = MockMvcRequestBuilders
                .get("/users")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
//                .andExpect(content().json("{\n" +
//                        "    \"name\": \"zenj\",\n" +
//                        "    \"birthDate\": \"2021-03-11T08:19:37.063+00:00\"\n" +
//                        "}"))
                .andReturn();

    }

}
