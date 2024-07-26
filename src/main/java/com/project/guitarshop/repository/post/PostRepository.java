package com.project.guitarshop.repository.post;

import com.project.guitarshop.entity.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
