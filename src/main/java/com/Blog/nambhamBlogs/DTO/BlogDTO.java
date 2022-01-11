package com.Blog.nambhamBlogs.DTO;

import lombok.Data;



@Data
public class BlogDTO {

    private String userId;

    private String topic;


    private String content;
    private String type;
}
