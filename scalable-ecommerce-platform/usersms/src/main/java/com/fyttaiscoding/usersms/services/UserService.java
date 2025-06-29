package com.fyttaiscoding.usersms.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fyttaiscoding.usersms.models.User;
import com.fyttaiscoding.usersms.repositories.IUserRepository;
import com.fyttaiscoding.usersms.repositories.InMemoryUserRepository;

@Service
public class UserService {

    private final IUserRepository userRepository;

    public UserService(InMemoryUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        userRepository.save(user);
        return user;
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> updateUser(Long id, User userDetails) {
        if (userRepository.findById(id).isEmpty()) return Optional.empty();
        userDetails.setId(id);
        return userRepository.update(userDetails);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
