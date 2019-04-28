package com.example.demo.mediaPublish.user.api;

import com.example.demo.mediaPublish.user.model.User;
import com.example.demo.mediaPublish.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/user")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "{id}")
    public User getUserByID(@PathVariable("id") UUID id){
        return userService.getUsersByID(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUserByID(@PathVariable("id") UUID id) {
        userService.deleteUsersByID(id);
    }

    @PutMapping(path = "{id}")
    public int updateUserByID (@PathVariable("id")UUID id, @RequestBody User user){
        return userService.updateUserByID(id, user);
    }
}
