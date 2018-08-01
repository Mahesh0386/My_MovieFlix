package main.java.io.bhannur.api.service;

import main.java.io.bhannur.api.entity.User;
import main.java.io.bhannur.api.exception.BadRequestException;
import main.java.io.bhannur.api.exception.EntityNotFoundException;
import main.java.io.bhannur.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User findOne(String id) {
        User emp = repository.findOne(id);
        if (emp == null) {
            throw new EntityNotFoundException("User not found");
        }
        return emp;
    }

    @Override
    @Transactional
    public User createUser(User user) {
        User existing = repository.findByEmail(user.getEmail());
        if (existing != null) {
            throw new BadRequestException("User with this email already exists");
        }
        return repository.create(user);
    }

    @Override
    @Transactional
    public User update(String id, User user) {
        User existing = repository.findOne(id);
        if (existing == null) {
            throw new EntityNotFoundException("User not found");
        }
        return repository.update(user);
    }

    @Override
    @Transactional
    public void delete(String id) {
        User existing = repository.findOne(id);
        if (existing == null) {
            throw new EntityNotFoundException("User not found");
        }
        repository.delete(existing);
    }
}
