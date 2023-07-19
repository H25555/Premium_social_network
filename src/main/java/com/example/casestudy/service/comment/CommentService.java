package com.example.casestudy.service.comment;

import com.example.casestudy.model.Comment;
import com.example.casestudy.repository.CommentRepository;
import com.example.casestudy.repository.UserRepository;
import com.example.casestudy.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.OpenOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    public Comment saveComment(Comment comment){
        comment.setUser(userService.getUserById(comment.getUser().getId()).get());
        return commentRepository.save(comment);
    }
    public Optional<Comment> getCommentById(UUID id){
        return commentRepository.findById(id);
    }
    public void deleteComment(Comment comment){
        commentRepository.delete(comment);
    }
    public List<Comment> getCommentByPostId(UUID id){
        return commentRepository.getCommentByPostId(id);
    }
}
