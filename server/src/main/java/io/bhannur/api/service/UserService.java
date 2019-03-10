package main.java.io.bhannur.api.service;

import main.java.io.bhannur.api.entity.User;
import main.java.io.bhannur.api.exception.UserApiExceptions;

import java.util.List;

public interface UserService {

         List<User> findAll();

         User findOne(String id);

         User createUser(User user) throws UserApiExceptions;

         User update(String id, User user);

         void deleteUser(String id);
    }

