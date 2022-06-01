package com.Blog.nambhamBlogs.contoller;

import com.Blog.nambhamBlogs.DTO.BlogDTO;
import com.Blog.nambhamBlogs.DTO.UniversalResponseDTO;
import com.Blog.nambhamBlogs.DTO.UpdateBlogDTO;
import com.Blog.nambhamBlogs.DTO.UserDTO;
import com.Blog.nambhamBlogs.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Blogs")
public class BlogController {
    @Autowired
    BlogService blogService;
    @PostMapping(value="/addBlog")
    public ResponseEntity<?> addUser(@RequestBody BlogDTO blogDTO){
        UniversalResponseDTO ur = blogService.addBlog(blogDTO);
        return new ResponseEntity<>(ur, HttpStatus.OK);
    }
    @GetMapping(value="/fetchBlogByTopic",params = "topic")
    public ResponseEntity<?> fetchBlogByTopic(@RequestParam String topic){
        UniversalResponseDTO ur = blogService.fetchBlogByTopic(topic);
        return new ResponseEntity<>(ur, HttpStatus.OK);
    }
    @GetMapping(value="/fetchBlogById",params = "blogId")
    public ResponseEntity<?> fetchBlogById(@RequestParam String blogId){
        UniversalResponseDTO ur = blogService.fetchBlogById(blogId);
        return new ResponseEntity<>(ur, HttpStatus.OK);
    }
    @GetMapping(value="/fetchBlogByUserId",params = "userId")
    public ResponseEntity<?> fetchBlogByUserId(@RequestParam String userId){
        UniversalResponseDTO ur = blogService.fetchBlogByUserId(userId);
        return new ResponseEntity<>(ur, HttpStatus.OK);
    }
    @PostMapping(value="/updateBlog")
    public ResponseEntity<?> updateBlog(@RequestBody UpdateBlogDTO updateBlogDTO){
        UniversalResponseDTO ur = blogService.updateBlog(updateBlogDTO);
        return new ResponseEntity<>(ur, HttpStatus.OK);
    }
    @GetMapping(value="/deleteBlog",params = "blogId")
    public ResponseEntity<?> deleteBlog(@RequestParam String blogId){
        UniversalResponseDTO ur = blogService.deleteBlog(blogId);
        return new ResponseEntity<>(ur, HttpStatus.OK);
    }
    //shivani
}
