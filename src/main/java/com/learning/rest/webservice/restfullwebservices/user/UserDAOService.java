package com.learning.rest.webservice.restfullwebservices.user;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserDAOService {
    private static List<User> users = new ArrayList<>();

    private static int userCount = 3;

    static  {
        users.add(new User(1, "zenj", new Date()));
        users.add(new User(2, "jutahi", new Date()));
        users.add(new User(3, "zwanji", new Date()));
    }

    // Methods
    //getallUsers() method

    public List<User> getUsers() {
        return users;
    }

    // save users
    public User save(User user){
        if(user.getId()==null){
            user.setId(++userCount);
        }
        users.add(user);
        return user;
    }

    // find one user
    public  User getOne(int id){
        for(User user: users) {
            if(user.getId()==id){
                return user;
            }
        }
        return null;
    }
}
