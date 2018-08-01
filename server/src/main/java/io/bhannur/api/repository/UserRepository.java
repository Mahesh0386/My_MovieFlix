package main.java.io.bhannur.api.repository;

import main.java.io.bhannur.api.entity.User;

import java.util.List;

public interface UserRepository {

     List<User> findAll();

     User findOne(String id);

     User findByEmail(String email);

     User createUser(User user);

     User update(User user);

     void deleteUser(User user);


}
