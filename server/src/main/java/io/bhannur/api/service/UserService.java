package io.bhannur.api.service;

import io.bhannur.api.entity.User;
import io.bhannur.api.exception.BadRequestException;
import io.bhannur.api.exception.UserApiExceptions;

import java.util.List;

public interface UserService {

         List<User> findAll();

         User findOne(String id);

         User createUser(User user) throws UserApiExceptions, BadRequestException;

         User update(String id, User user);

         void deleteUser(String id);
    }

