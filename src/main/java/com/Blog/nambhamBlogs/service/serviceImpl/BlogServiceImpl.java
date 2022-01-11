package com.Blog.nambhamBlogs.service.serviceImpl;

import com.Blog.nambhamBlogs.DTO.BlogDTO;
import com.Blog.nambhamBlogs.DTO.ResponseCodeJson;
import com.Blog.nambhamBlogs.DTO.UniversalResponseDTO;
import com.Blog.nambhamBlogs.DTO.UpdateBlogDTO;
import com.Blog.nambhamBlogs.model.Blogs;
import com.Blog.nambhamBlogs.repository.BlogRepository;
import com.Blog.nambhamBlogs.service.BlogService;
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
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogRepository blogRepository;
    @Autowired
    UserService userService;
    @Override
    public UniversalResponseDTO addBlog(BlogDTO blogDTO) {
        UniversalResponseDTO ur=new UniversalResponseDTO();
        if(!userService.validateUser(blogDTO.getUserId())){
            ur.setResponseCodeJson(new ResponseCodeJson("no usch user found", HttpStatus.CONFLICT.value()));
            return ur;
        }
        Blogs blogs=new Blogs();
        BeanUtils.copyProperties(blogDTO,blogs);
        blogs.setBlogId(IdGenerator.getUUId());
        blogs.setIsDeleted(NOT_DELETED);
        blogs=blogRepository.save(blogs);
        ur.setObject(blogs);
        ur.setResponseCodeJson(new ResponseCodeJson("blogs saved",HttpStatus.OK.value()));

        return ur;
    }

    @Override
    public UniversalResponseDTO fetchBlogByTopic(String topic) {
        UniversalResponseDTO ur=new UniversalResponseDTO();
        List<Blogs> blogsList=blogRepository.findByTopicAndIsDeleted(topic,NOT_DELETED);
        if(blogsList.isEmpty()){
            ur.setResponseCodeJson(new ResponseCodeJson("no list found for that topic",HttpStatus.CONFLICT.value()));
            return ur;
        }
        ur.setList(blogsList);
        ur.setResponseCodeJson(new ResponseCodeJson("list of blog found",HttpStatus.OK.value()));
        return ur;
    }

    @Override
    public UniversalResponseDTO fetchBlogByUserId(String userId) {
        UniversalResponseDTO ur=new UniversalResponseDTO();
        List<Blogs> blogsList=blogRepository.findByUserIdAndIsDeleted(userId,NOT_DELETED);
        if(blogsList.isEmpty()){
            ur.setResponseCodeJson(new ResponseCodeJson("no list found for that topic",HttpStatus.CONFLICT.value()));
            return ur;
        }
        ur.setList(blogsList);
        ur.setResponseCodeJson(new ResponseCodeJson("list of blog found",HttpStatus.OK.value()));
        return ur;
    }

    @Override
    public UniversalResponseDTO fetchBlogById(String blogId) {
        UniversalResponseDTO ur=new UniversalResponseDTO();
        Blogs blogs=blogRepository.findByBlogIdAndIsDeleted(blogId,NOT_DELETED);
        if(blogs==null){
            ur.setResponseCodeJson(new ResponseCodeJson("no blog found with that id",HttpStatus.CONFLICT.value()));
            return ur;
        }
        ur.setObject(blogs);
        ur.setResponseCodeJson(new ResponseCodeJson("blog found",HttpStatus.OK.value()));

        return ur;
    }

    @Override
    public UniversalResponseDTO updateBlog(UpdateBlogDTO updateBlogDTO) {
        UniversalResponseDTO ur=new UniversalResponseDTO();
        Blogs blogs=blogRepository.findByBlogIdAndIsDeleted(updateBlogDTO.getBlogId(),NOT_DELETED);
        if(blogs==null){
            ur.setResponseCodeJson(new ResponseCodeJson("no blog found with that id",HttpStatus.CONFLICT.value()));
            return ur;
        }
        BeanUtils.copyProperties(updateBlogDTO,blogs,"blogId");
        blogs=blogRepository.save(blogs);
        ur.setObject(blogs);
        ur.setResponseCodeJson(new ResponseCodeJson("blog updated",HttpStatus.OK.value()));
        return ur;
    }

    @Override
    public UniversalResponseDTO deleteBlog(String blogId) {
        UniversalResponseDTO ur=new UniversalResponseDTO();
        Blogs blogs=blogRepository.findByBlogIdAndIsDeleted(blogId,NOT_DELETED);
        if(blogs==null){
            ur.setResponseCodeJson(new ResponseCodeJson("no blog found with that id",HttpStatus.CONFLICT.value()));
            return ur;
        }
        blogs.setIsDeleted(DELETED);
        blogs=blogRepository.save(blogs);
        ur.setObject(blogs);
        ur.setResponseCodeJson(new ResponseCodeJson("blog delted",HttpStatus.OK.value()));
        return ur;
    }
}
