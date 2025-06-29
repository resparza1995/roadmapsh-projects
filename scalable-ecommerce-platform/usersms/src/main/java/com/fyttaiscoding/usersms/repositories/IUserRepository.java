package com.fyttaiscoding.usersms.repositories;

import java.util.List;
import java.util.Optional;

import com.fyttaiscoding.usersms.models.User;

public interface IUserRepository {
    public boolean existsByEmail(String email);
    public void save(User user);
    public List<User> findAll();
    public Optional<User> findById(Long id);
    public Optional<User> update(User user);
    public void deleteById(Long id);
}
