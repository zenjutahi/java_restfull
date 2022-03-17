package com.learning.rest.webservice.restfullwebservices.support;

import com.learning.rest.webservice.restfullwebservices.posts.Post;
import com.learning.rest.webservice.restfullwebservices.user.User;

import java.util.Date;
import java.util.List;

public class UserBuilder {
    private Integer id;
    private String name;
    private Date birthdate;
    private List<Post> post;

    public UserBuilder() {
        name = "Zenj";
    }

//    public Integer getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public Date getBirthdate() {
//        return birthdate;
//    }
//
//    public List<Post> getPost() {
//        return post;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setBirthdate(Date birthdate) {
//        this.birthdate = birthdate;
//    }
//
//    public void setPost(List<Post> post) {
//        this.post = post;
//    }

    public static UserBuilder user() {
        return new UserBuilder();
    }

    public UserBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public UserBuilder birthdate(Date birthdate){
        this.birthdate = birthdate;
        return this;
    }

    public UserBuilder post(List post){
        this.post = post;
        return this;
    }

    public User build() {
        User user = new User(id, name, birthdate);
        user.setPosts(post);
        return user;
    }

}
