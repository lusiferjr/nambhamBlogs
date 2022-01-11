package com.Blog.nambhamBlogs.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "blogs", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Blogs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "blogId")
    private String blogId;
    @Column(name = "userId")
    private String userId;
    @Column(name = "topic")
    private String topic;

    @Column(name = "content",columnDefinition = "text")
    private String content;
    @Column(name = "type")
    private String type;
    @Column
    private Boolean isDeleted;
}
