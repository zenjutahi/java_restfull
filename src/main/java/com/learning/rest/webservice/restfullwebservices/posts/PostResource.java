package com.learning.rest.webservice.restfullwebservices.posts;


import com.learning.rest.webservice.restfullwebservices.user.User;
import com.learning.rest.webservice.restfullwebservices.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.util.ArrayUtils;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class PostResource {

    @Autowired
    private PostDAOService service;

    // Retrieves all posts
    @GetMapping("/posts")
    public List<Posts> retrieveAllPosts(){
        return service.getPosts();
    }

    //GET /users/{user_id}/posts
    // retrieveUserPosts(int id)
    @GetMapping("/users/{user_id}/posts")
    public List<Posts> retrieveUserPosts(@PathVariable int user_id){
        List<Posts> posts = service.getUserPosts(user_id);
        if(posts==null|| posts.isEmpty())
            throw new UserNotFoundException("user-id-" + user_id);
        return posts;
    }
    // POST /users/{id}/posts
    // create post for a user
    @PostMapping("/users/{user_id}/post")
    public Posts createPost(@Valid @RequestBody Posts post, @PathVariable int user_id){
        Posts savedPost = service.save(post, user_id);
        return savedPost;
    }
    // GET /users/{id}/posts/{post_id}
    @GetMapping("/users/{user_id}/posts/{post_id}")
    public EntityModel<Posts> RetrieveSinglePost(@PathVariable int user_id, @PathVariable int post_id ){
        Posts singlePost = service.getSinglePost(user_id,post_id);
        if(singlePost == null)
            throw new UserNotFoundException("user-id-" + user_id);
        EntityModel<Posts> model = EntityModel.of(singlePost);

        WebMvcLinkBuilder linkToOtherPosts =
                linkTo(methodOn(PostResource.class).retrieveUserPosts(user_id));
        model.add(linkToOtherPosts.withRel("user's-other-posts"));
        return model;
    }
    // DELETE /users/{id}/posts/{post_id}
    @DeleteMapping("/users/{user_id}/posts/{post_id}")
    public void DeleteSinglePost(@PathVariable int user_id, @PathVariable int post_id ){
        Posts singlePost = service.deleteById(user_id,post_id);
        if(singlePost == null)
            throw new UserNotFoundException("user-id-" + user_id);
    }

}
