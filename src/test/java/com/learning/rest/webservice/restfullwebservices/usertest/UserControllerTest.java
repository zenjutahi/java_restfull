package com.learning.rest.webservice.restfullwebservices.usertest;

import com.learning.rest.webservice.restfullwebservices.controller.ItemController;
import com.learning.rest.webservice.restfullwebservices.user.User;
import com.learning.rest.webservice.restfullwebservices.user.UserDAOService;
import com.learning.rest.webservice.restfullwebservices.user.UserJPAResource;
import com.learning.rest.webservice.restfullwebservices.user.UserResource;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Supplier;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserResource.class)
public class UserControllerTest {

    private Logger Log = LoggerFactory.getLogger(UserControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDAOService userDAOService;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
    String dateInString = "31-08-1982 10:20:56";
    Date date = sdf.parse(dateInString);

    public UserControllerTest() throws ParseException {
    }

    @Test
    public void userList_basic() throws Exception {

        // mock userDaoService

        when(userDAOService.getUsers()).thenReturn(
                Arrays.asList(new User(1, "zenj", date)));

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

}


