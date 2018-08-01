package main.java.io.bhannur.api.controller;


import main.java.io.bhannur.api.entity.User;
import main.java.io.bhannur.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="users")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> findAll() {
        return service.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public User findOne(@PathVariable("id") String userId) {
        return service.findOne(userId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public User create(@RequestBody User user) {
        return service.createUser(user);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    public User update(@PathVariable("id") String id, @RequestBody User user) {
        return service.update(id, user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void delete(@PathVariable("id") String id) {
        service.delete(id);
    }

}
