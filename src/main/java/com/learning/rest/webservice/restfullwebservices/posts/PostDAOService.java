package com.learning.rest.webservice.restfullwebservices.posts;

import com.learning.rest.webservice.restfullwebservices.user.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class PostDAOService {

    private static List<Posts> posts = new ArrayList<>();

    private static int postCount = 2;


    static  {
        posts.add(new Posts(1, 2, "First Book", "First book on Java spring Boot"));
        posts.add(new Posts(2, 2, "Second Book", "Second book on Java spring Boot"));
    }

    // methods
    // gets all posts
    public List<Posts> getPosts(){
        return posts;
    }

    // get all posts for a user
    public List<Posts> getUserPosts(int user_id){
        List<Posts> userPosts = new ArrayList<>();
        for(Posts post: posts){
            if(post.getUser_id()==user_id) {
                userPosts.add(post);
            }
            }
        return userPosts;

    }
    //Create a post for a user
    public Posts save(Posts post, int user_id){
        post.setUser_id(user_id);
        if(post.getPost_id()==null){
            post.setPost_id(++postCount);
        }
        posts.add(post);
        return post;
    }
    // Retrieve details of a post
    public Posts getSinglePost(int user_id, int post_id){
        for(Posts post: posts){
            if((post.getPost_id()==post_id) & (post.getUser_id()==user_id)){
                return post;
            }
        }
        return null;
    }
}
