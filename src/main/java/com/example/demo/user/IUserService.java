package com.example.demo.user;

import java.util.List;

public interface IUserService {

    void add(User user);

    List<User> getAll();

    void update(User user);

    void deleteById(Long id);

    User getById(Long id);
}
