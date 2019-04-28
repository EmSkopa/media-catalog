package com.example.demo.mediaPublish.user.service;

import com.example.demo.mediaPublish.user.dao.UserRepository;
import com.example.demo.mediaPublish.user.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user){
        this.userRepository.insert(user);
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public Optional<User> getUsersByID(UUID id){
        return this.userRepository.findById(id);
    }

    public void deleteUsersByID(UUID id) {
        this.userRepository.deleteById(id);
    }

    public int updateUserByID(UUID id, User user){

        if(this.userRepository.findById(id).isEmpty()) {
            return 0;
        }
        else
        {
            this.userRepository.save(new User(id,
                    user.getUserName()));

            return 1;
        }
    }
}
