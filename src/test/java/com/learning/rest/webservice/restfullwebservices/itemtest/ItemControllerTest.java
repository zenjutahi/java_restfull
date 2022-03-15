package com.learning.rest.webservice.restfullwebservices.itemtest;

import com.learning.rest.webservice.restfullwebservices.controller.ItemController;
import com.learning.rest.webservice.restfullwebservices.helloworld.HelloWorldController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ItemController.class)
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void dummyItem_basic() throws Exception {
        // call "/hello-world" format application/json
        RequestBuilder request = MockMvcRequestBuilders
                .get("/dummy-item")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"Ball\",\"quantity\":10,\"price\":100}"))
                .andReturn();

        // verify "Hello World"
//        assertEquals("Hello World", mvcResult.getResponse().getContentAsString());

    }

}
