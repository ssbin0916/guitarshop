package com.project.guitarshop.board.repository;

import com.project.guitarshop.board.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
