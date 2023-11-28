package com.gonie.fullStackbackend.controller;

import com.gonie.fullStackbackend.exception.UserNotFoundException;
import com.gonie.fullStackbackend.model.User;
import com.gonie.fullStackbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired //inject user repository interface
    private UserRepository userRepository;

    @PostMapping("/user") //posting the data to the user table
    User newUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @GetMapping("/users") //getting the use from the database
    List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}") //get the user by id
    User getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/user/{id}") //update user
    User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}") //deleting the user
    String deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return  "User with id "+id+" has been deleted success.";
    }



}
