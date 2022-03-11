package com.learning.rest.webservice.restfullwebservices.helloworld;

import org.springframework.web.bind.annotation.*;

//Controller
@RestController
public class HelloWorldController {

    //GET and URI - /hello-world
    // method - that returns "Hello world"
    /*
    First way of mapping requests in Spring boot
    @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    public String helloWorld(){
        return "Hello World";
    }
    */
    //another way of mapping requests
    @GetMapping(path = "/hello-world")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World Today");
    }

    // simple resource mapping with path variable
    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }
}
