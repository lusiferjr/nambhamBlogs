package com.Blog.nambhamBlogs.service.serviceImpl;

import com.Blog.nambhamBlogs.DTO.ResponseCodeJson;
import com.Blog.nambhamBlogs.DTO.UniversalResponseDTO;
import com.Blog.nambhamBlogs.DTO.UserDTO;
import com.Blog.nambhamBlogs.model.User;
import com.Blog.nambhamBlogs.repository.UserRepository;
import com.Blog.nambhamBlogs.service.UserService;
import com.Blog.nambhamBlogs.util.IdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UniversalResponseDTO addUser(UserDTO userDTO) {
        UniversalResponseDTO ur = new UniversalResponseDTO<>();
        if(userDTO.getEmail().equalsIgnoreCase("")){
            ur.setResponseCodeJson(new ResponseCodeJson("user not provided email", HttpStatus.NO_CONTENT.value()));
            return ur;
        }
        User user = new User();

        BeanUtils.copyProperties(userDTO,user);
        user.setUserId(IdGenerator.getUUId());
        user = userRepository.save(user);
        if(user==null){
            ur.setResponseCodeJson(new ResponseCodeJson("user not saved", HttpStatus.CONFLICT.value()));
            return ur;
        }
        ur.setObject(user);
        ur.setResponseCodeJson(new ResponseCodeJson("user created", HttpStatus.OK.value()));
        return ur;
    }
}
