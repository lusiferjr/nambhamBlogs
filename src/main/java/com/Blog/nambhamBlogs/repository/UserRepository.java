package com.Blog.nambhamBlogs.repository;

import com.Blog.nambhamBlogs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findByUsernameAndIsDeleted(String username, Boolean notDeleted);

    User findByEmailIdAndIsDeleted(String emailId, Boolean notDeleted);
}
