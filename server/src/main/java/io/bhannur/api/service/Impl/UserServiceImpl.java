package io.bhannur.api.service.Impl;

import io.bhannur.api.entity.User;
import io.bhannur.api.exception.BadRequestException;
import io.bhannur.api.exception.EntityNotFoundException;
import io.bhannur.api.exception.UserApiExceptions;
import io.bhannur.api.repository.UserRepository;
import io.bhannur.api.service.UserService;
import io.bhannur.api.utility.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public User createUser(User user) throws UserApiExceptions, BadRequestException {
        if (!checkEmail(user.getEmail())) {
            throw new UserApiExceptions("Email not valid");
        }
        User existing = repository.findByEmail(user.getEmail());
        if (existing != null) {
            throw new BadRequestException("User with this email already exists");
        }
        user.setRole(CommonConstants.System.USER_ROLE);
        return repository.createUser(user);

    }

    private boolean checkEmail(String email) {
        final Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
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
    public void deleteUser(String id) {
        User existing = repository.findOne(id);
        if (existing == null) {
            throw new EntityNotFoundException("User not found");
        }
        repository.deleteUser(existing);
    }
}
