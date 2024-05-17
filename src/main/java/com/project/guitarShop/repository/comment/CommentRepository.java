package com.project.guitarShop.repository.comment;

import com.project.guitarShop.entity.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
