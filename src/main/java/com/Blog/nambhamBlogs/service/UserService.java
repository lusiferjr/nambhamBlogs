package com.Blog.nambhamBlogs.service;

import com.Blog.nambhamBlogs.DTO.UniversalResponseDTO;
import com.Blog.nambhamBlogs.DTO.UserDTO;
import com.Blog.nambhamBlogs.DTO.UserUpdateDTO;

public interface UserService {
    UniversalResponseDTO addUser(UserDTO userDTO);

    UniversalResponseDTO getUsersWithName(String username);

    UniversalResponseDTO getUserByEmailId(String emailId);

    UniversalResponseDTO deleteUser(String userId);

    UniversalResponseDTO updateUser(UserUpdateDTO userUpdate);
    Boolean validateUser(String userId);
}
