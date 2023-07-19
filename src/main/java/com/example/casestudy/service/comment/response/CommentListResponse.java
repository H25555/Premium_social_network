package com.example.casestudy.service.comment.response;

import com.example.casestudy.model.Comment;
import com.example.casestudy.model.Content;
import com.example.casestudy.model.Post;
import com.example.casestudy.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentListResponse {
    private UUID id;
    private User user;
    private Content content;
    private Post post;
    private Comment parentComment;
}
