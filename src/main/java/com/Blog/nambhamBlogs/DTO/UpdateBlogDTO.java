package com.Blog.nambhamBlogs.DTO;

import lombok.Data;

@Data
public class UpdateBlogDTO {
    private String blogId;


    private String topic;


    private String content;
    private String type;
}
