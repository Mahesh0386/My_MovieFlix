package main.java.io.bhannur.api.service;

import main.java.io.bhannur.api.entity.User;

import java.util.List;

public interface UserService {

         List<User> findAll();

         User findOne(String id);

         User create(User user);

         User update(String id, User user);

         void delete(String id);
    }

