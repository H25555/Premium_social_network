package com.example.casestudy.repository;

import com.example.casestudy.model.Comment;
import com.example.casestudy.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
    
}
