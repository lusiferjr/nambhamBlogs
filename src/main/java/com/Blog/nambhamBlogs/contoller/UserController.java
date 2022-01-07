package com.Blog.nambhamBlogs.contoller;

import com.Blog.nambhamBlogs.DTO.UniversalResponseDTO;
import com.Blog.nambhamBlogs.DTO.UserDTO;
import com.Blog.nambhamBlogs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value="/addUser")
    public ResponseEntity<?> addUser(@RequestBody UserDTO userDTO){
        UniversalResponseDTO ur = userService.addUser(userDTO);
        return new ResponseEntity<>(ur, HttpStatus.OK);
    }
}
