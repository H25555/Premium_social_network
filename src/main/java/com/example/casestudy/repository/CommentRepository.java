package com.example.casestudy.repository;

import com.example.casestudy.model.Comment;
import com.example.casestudy.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
@Query("select Comment from Comment where Comment.post.id = : postId")

    List<Comment> getCommentByPostId(@Param("postId") UUID postId);
}
