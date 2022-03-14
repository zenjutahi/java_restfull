package com.learning.rest.webservice.restfullwebservices.posts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learning.rest.webservice.restfullwebservices.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Post {
    // id
    @Id
    @GeneratedValue
    private Integer id;

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch= FetchType.LAZY )
    @JsonIgnore
    public User user;

    // title
    @Size(min=5, message = "name should have at least 5 characters")
    private String title;
    // body
    @Size(min=12, message = "name should have at least 12 characters")
    private String body;

    private Post() {
    }

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }


    public String getTitle() {
        return title;
    }
    public String getBody() {
        return body;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Posts{" +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
