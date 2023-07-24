package com.example.casestudy.service.comment;

import com.example.casestudy.model.*;
import com.example.casestudy.repository.CommentRepository;
import com.example.casestudy.repository.ContentCommentRepo;
import com.example.casestudy.repository.PostRepository;
import com.example.casestudy.repository.UserRepository;
import com.example.casestudy.service.comment.request.CommentSaveRequest;
import com.example.casestudy.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.OpenOption;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ContentCommentRepo contentCommentRepo;

    public Comment saveComment(CommentSaveRequest commentSaveRequest, User user){
        Comment comment = new Comment();
        comment.setUser(user);
        Post post = postRepository.findById(Long.valueOf(commentSaveRequest.getId())).get();
comment.setPost(post);
        ContentComment content = new ContentComment();
        content.setText(commentSaveRequest.getContent());
        contentCommentRepo.save(content);
comment.setContentComment(content);
        return commentRepository.save(comment);
    }
    public Optional<Comment> getCommentById(Long id){
        return commentRepository.findById(id);
    }
    public void deleteComment(Comment comment){
        commentRepository.delete(comment);
    }
    public List<Comment> getCommentByPostId(Long id){
        Post post = postRepository.findById(id).get();
        return commentRepository.findByPostContaining(post);
    }
}
