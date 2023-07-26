package com.example.casestudy.controller.rest;

import com.example.casestudy.model.Content;
import com.example.casestudy.model.Like;
import com.example.casestudy.model.Post;
import com.example.casestudy.model.User;
import com.example.casestudy.repository.UserRepository;
import com.example.casestudy.service.UploadService;
import com.example.casestudy.service.like.LikeService;
import com.example.casestudy.service.post.PostService;
import com.example.casestudy.service.post.request.PostResponse;
import com.example.casestudy.service.post.request.PostSaveRequest;
import com.example.casestudy.utils.AppUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostRestController {
    public final PostService postService;
    public final UploadService uploadService;
    public final UserRepository userRepository;
    public final LikeService likeService;


    @GetMapping
    public ResponseEntity<?> getAllPosts() {

        List<Post> posts = postService.getAllPost();

        return ResponseEntity.ok(posts);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestParam("posttextcontent") String text,
                           @RequestParam("fileInput")MultipartFile[] fileInput, Authentication authentication) throws IOException {
        PostSaveRequest request = new PostSaveRequest();
        PostSaveRequest.ContentSaveRequest contentSaveRequest = new PostSaveRequest.ContentSaveRequest();
        MultipartFile file = fileInput[0];
        if(fileInput != null && file.getSize() > 0) {
            List<PostSaveRequest.ContentSaveRequest.MediaSaveRequest> media = uploadService.transferFiles(fileInput);
            contentSaveRequest.setMedia(media);
        }
        request.setContent(contentSaveRequest);
        request.getContent().setText(text);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Post postCreate = postService.createPost(request,userDetails);
      return   ResponseEntity.ok(postCreate);

    }
}
