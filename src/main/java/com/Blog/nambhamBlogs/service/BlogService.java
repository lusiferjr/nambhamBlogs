package com.Blog.nambhamBlogs.service;

import com.Blog.nambhamBlogs.DTO.BlogDTO;
import com.Blog.nambhamBlogs.DTO.UniversalResponseDTO;
import com.Blog.nambhamBlogs.DTO.UpdateBlogDTO;

public interface BlogService {
    UniversalResponseDTO addBlog(BlogDTO blogDTO);

    UniversalResponseDTO fetchBlogByTopic(String topic);

    UniversalResponseDTO fetchBlogByUserId(String userId);

    UniversalResponseDTO fetchBlogById(String blogId);

    UniversalResponseDTO updateBlog(UpdateBlogDTO updateBlogDTO);

    UniversalResponseDTO deleteBlog(String blogId);
}
