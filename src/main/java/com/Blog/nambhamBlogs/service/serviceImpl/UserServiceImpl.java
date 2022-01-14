package com.Blog.nambhamBlogs.service.serviceImpl;

import com.Blog.nambhamBlogs.DTO.ResponseCodeJson;
import com.Blog.nambhamBlogs.DTO.UniversalResponseDTO;
import com.Blog.nambhamBlogs.DTO.UserDTO;
import com.Blog.nambhamBlogs.DTO.UserUpdateDTO;
import com.Blog.nambhamBlogs.constants.Constants;
import com.Blog.nambhamBlogs.model.User;
import com.Blog.nambhamBlogs.repository.UserRepository;
import com.Blog.nambhamBlogs.service.UserService;
import com.Blog.nambhamBlogs.util.IdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.Blog.nambhamBlogs.constants.Constants.DELETED;
import static com.Blog.nambhamBlogs.constants.Constants.NOT_DELETED;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UniversalResponseDTO addUser(UserDTO userDTO) {
        UniversalResponseDTO ur = new UniversalResponseDTO();
        if(userDTO.getEmailId().equalsIgnoreCase("")){
            ur.setResponseCodeJson(new ResponseCodeJson("user not provided email", HttpStatus.NO_CONTENT.value()));
            return ur;
        }
        User user = new User();

        BeanUtils.copyProperties(userDTO,user);
        user.setUserId(IdGenerator.getUUId());
        user.setIsDeleted(Constants.NOT_DELETED);
        user = userRepository.save(user);
        if(user==null){
            ur.setResponseCodeJson(new ResponseCodeJson("user not saved", HttpStatus.CONFLICT.value()));
            return ur;
        }

        ur.setObject(user);
        ur.setResponseCodeJson(new ResponseCodeJson("user created", HttpStatus.OK.value()));
        return ur;
    }

    @Override
    public UniversalResponseDTO getUsersWithName(String username) {
        UniversalResponseDTO ur = new UniversalResponseDTO();
        List<User> users = userRepository.findByUsernameAndIsDeleted(username,Constants.NOT_DELETED);
        ur.setList(users);
        ur.setResponseCodeJson(new ResponseCodeJson("users successfully fetched.",HttpStatus.OK.value()));
        return ur;
    }

    @Override
    public UniversalResponseDTO getUserByEmailId(String emailId) {
        UniversalResponseDTO ur = new UniversalResponseDTO();
        User user  = userRepository.findByEmailIdAndIsDeleted(emailId,Constants.NOT_DELETED);
        if(user==null){
            ur.setResponseCodeJson(new ResponseCodeJson("user not found",HttpStatus.CONFLICT.value()));
            return ur;
        }
        ur.setObject(user);
        ur.setResponseCodeJson(new ResponseCodeJson("user successfully fetched.",HttpStatus.OK.value()));
        return ur;
    }

    @Override
    public UniversalResponseDTO deleteUser(String userId) {
        UniversalResponseDTO ur=new UniversalResponseDTO();
        User user  = userRepository.findByUserIdAndIsDeleted(userId,Constants.NOT_DELETED);
        if(user==null){
            ur.setResponseCodeJson(new ResponseCodeJson("no such user found",HttpStatus.CONFLICT.value()));
            return ur;
        }
        user.setIsDeleted(DELETED);
        user=userRepository.save(user);
        if(user==null){
            ur.setResponseCodeJson(new ResponseCodeJson(" user not created",HttpStatus.CONFLICT.value()));
            return ur;
        }
        ur.setObject(user);
        ur.setResponseCodeJson(new ResponseCodeJson("user deleted",HttpStatus.OK.value()));
        return ur;
    }

    @Override
    public UniversalResponseDTO updateUser(UserUpdateDTO userUpdate) {
            UniversalResponseDTO ur=new UniversalResponseDTO();
        User user  = userRepository.findByUserIdAndIsDeleted(userUpdate.getUserId(),Constants.NOT_DELETED);
        if(user==null){
            ur.setResponseCodeJson(new ResponseCodeJson("no such user found",HttpStatus.CONFLICT.value()));
            return ur;
        }
        BeanUtils.copyProperties(userUpdate,user,"userId");
        user=userRepository.save(user);
        if(user==null){
            ur.setResponseCodeJson(new ResponseCodeJson(" user not created",HttpStatus.CONFLICT.value()));
            return ur;
        }
        ur.setObject(user);
        ur.setResponseCodeJson(new ResponseCodeJson("user updated",HttpStatus.OK.value()));
        return ur;
    }

    @Override
    public Boolean validateUser(String userId) {
        User user=userRepository.findByUserIdAndIsDeleted(userId,NOT_DELETED);
        if(user==null) return false;
        else return true;
    }
}
