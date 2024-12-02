package com.example.demo.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{

    private final UserRepository userRepository;

    //add new user
    @Override
    public void add(User user){
        userRepository.save(user);
    }

    //get all users
    @Override
    public List<User> getAll(){
        return userRepository.findAll();
    }

    //update a user
    @Override
    public void update(User user){
        userRepository.save(user);
    }

    //delete user by id
    @Override
    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

    //get user by id
    @Override
    public User getById(Long id){
        return userRepository.findById(id).get();
    }
}
