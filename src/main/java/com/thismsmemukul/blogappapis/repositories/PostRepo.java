package com.thismsmemukul.blogappapis.repositories;

import com.thismsmemukul.blogappapis.entities.Category;
import com.thismsmemukul.blogappapis.entities.Post;
import com.thismsmemukul.blogappapis.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PostRepo extends JpaRepository<Post, UUID> {
    Page<Post> findByUser(User user, Pageable pageable);

    Page<Post> findByCategory(Category category,Pageable pageable);

}
