package com.example.casestudy.controller.rest;

import com.example.casestudy.model.Comment;
import com.example.casestudy.repository.CommentRepository;
import com.example.casestudy.service.comment.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/comment")
@AllArgsConstructor
public class RestComment {
    private final CommentRepository commentRepository;
    private final CommentService commentService;

    @PostMapping
    public Comment saveComment(@RequestBody Comment comment){
        return commentService.saveComment(comment);
    }
    @GetMapping
    public List<Comment> getAllComment(@PathVariable("postId") UUID postId){
        return commentService.getCommentByPostId(postId);
    }
}
