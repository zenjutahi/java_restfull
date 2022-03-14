package com.learning.rest.webservice.restfullwebservices.user;

import com.learning.rest.webservice.restfullwebservices.posts.Post;
import com.learning.rest.webservice.restfullwebservices.posts.PostDAOService;
import com.learning.rest.webservice.restfullwebservices.posts.PostResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.Resource;
import javax.swing.text.html.parser.Entity;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJPAResource {

    @Autowired
    private UserDAOService service;

    @Autowired
    private UserRepository userRepository;

    //GET /users
    // retrieveAllUsers
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers(){

        return userRepository.findAll();
    }

    //GET /users/{id}
    // retrieveUser(int id)
    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent())
            throw new UserNotFoundException("id-" + id);

        EntityModel<User> model = EntityModel.of(user.get());

        WebMvcLinkBuilder linkToUsers =
                linkTo(methodOn(this.getClass()).retrieveAllUsers());
        WebMvcLinkBuilder linkToPosts =
                linkTo(methodOn(PostResource.class).retrieveUserPosts(id));
        model.add(linkToUsers.withRel("all-users"));
        model.add(linkToPosts.withRel("user-posts"));
        return model;
    }
    // Delete user
    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id){
        userRepository.deleteById(id);
    }
    // POST
    // input - details of user
    // output - Created and return created user
    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = userRepository.save(user);
        // Return Created message and status code
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    //GET posts for users
    // retrieveAll posts for a user
    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrieveAllForUser(@PathVariable int id){
        Optional<User> userOptional = userRepository.findById(id);
        if(!userOptional.isPresent()){
            throw new UserNotFoundException("id-" +id );
        }
        List<Post> userPosts = userOptional.get().getPosts();
        return userPosts;
    }

}
