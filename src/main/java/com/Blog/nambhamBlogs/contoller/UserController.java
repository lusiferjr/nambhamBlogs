package com.Blog.nambhamBlogs.contoller;

import com.Blog.nambhamBlogs.DTO.UniversalResponseDTO;
import com.Blog.nambhamBlogs.DTO.UserDTO;
import com.Blog.nambhamBlogs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value="/addUser")
    public ResponseEntity<?> addUser(@RequestBody UserDTO userDTO){
        UniversalResponseDTO ur = userService.addUser(userDTO);
        return new ResponseEntity<>(ur, HttpStatus.OK);
    }

    @GetMapping(value="/getuser", params="username")
    public ResponseEntity<?> getUsers(@RequestParam String username){
        UniversalResponseDTO ur = userService.getUsersWithName(username);
        return new ResponseEntity<>(ur, HttpStatus.OK);
    }

    @GetMapping(value="/getuser", params="emailId")
    public ResponseEntity<?> getUser(@RequestParam String emailId){
        UniversalResponseDTO ur = userService.getUserByEmailId(emailId);
        return new ResponseEntity<>(ur, HttpStatus.OK);
    }
}
