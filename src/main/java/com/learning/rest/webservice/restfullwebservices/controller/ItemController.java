package com.learning.rest.webservice.restfullwebservices.controller;

import com.learning.rest.webservice.restfullwebservices.model.Item;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

    @GetMapping("/dummy-item")
    public Item displayItem(){
        return new Item(1, "Ball", 10, 100);
    }
}
