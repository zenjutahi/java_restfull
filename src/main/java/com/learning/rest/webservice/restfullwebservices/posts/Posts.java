package com.learning.rest.webservice.restfullwebservices.posts;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Posts {
    // post_id
    private Integer post_id;
    // user_id
    private Integer user_id;
    // title
    @Size(min=5, message = "name should have at least 5 characters")
    private String title;
    // body
    @Size(min=12, message = "name should have at least 12 characters")
    private String body;

    public Posts(Integer post_id, Integer user_id, String title, String body) {
        this.post_id = post_id;
        this.user_id = user_id;
        this.title = title;
        this.body = body;
    }

    public Integer getPost_id() {
        return post_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public void setPost_id(Integer post_id) {
        this.post_id = post_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
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
                "post_id=" + post_id +
                ", user_id=" + user_id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
