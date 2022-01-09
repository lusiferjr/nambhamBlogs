package com.Blog.nambhamBlogs.repository;

import com.Blog.nambhamBlogs.model.Blogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blogs,Long> {
    List<Blogs> findByTopicAndDeleted(String topic, Boolean notDeleted);

    List<Blogs> findByUserIdAndDeleted(String userId, Boolean notDeleted);

    Blogs findByBlogIdAndDeleted(String blogId, Boolean notDeleted);
}
