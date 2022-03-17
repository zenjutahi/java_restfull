package com.learning.rest.webservice.restfullwebservices.user;

import com.learning.rest.webservice.restfullwebservices.posts.PostDAOService;
import com.learning.rest.webservice.restfullwebservices.posts.PostResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.swing.text.html.parser.Entity;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDAOService service;

    //GET /users
    // retrieveAllUsers
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.getUsers();
    }

    //GET /users/{id}
    // retrieveUser(int id)
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id){
        User user = service.getOne(id);
        if(user==null)
            throw new UserNotFoundException("id-" + id);
        EntityModel<User> model = EntityModel.of(user);

        WebMvcLinkBuilder linkToUsers =
                linkTo(methodOn(this.getClass()).retrieveAllUsers());
        WebMvcLinkBuilder linkToPosts =
                linkTo(methodOn(PostResource.class).retrieveUserPosts(id));
        model.add(linkToUsers.withRel("all-users"));
        model.add(linkToPosts.withRel("user-posts"));
        return model;
    }
    // Delete user
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = service.deleteById(id);
        if(user==null)
            throw new UserNotFoundException("id-" + id);
    }
    // POST
    // input - details of user
    // output - Created and return created user
    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        User savedUser = service.save(user);
        // Return Created message and status code
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return user;
    }
}
