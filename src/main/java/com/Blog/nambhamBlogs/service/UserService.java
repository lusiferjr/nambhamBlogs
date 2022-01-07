package com.Blog.nambhamBlogs.service;

import com.Blog.nambhamBlogs.DTO.UniversalResponseDTO;
import com.Blog.nambhamBlogs.DTO.UserDTO;

public interface UserService {
    UniversalResponseDTO addUser(UserDTO userDTO);
}
