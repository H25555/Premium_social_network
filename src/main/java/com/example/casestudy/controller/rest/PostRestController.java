package com.example.casestudy.controller.rest;

import com.example.casestudy.service.post.PostService;
import com.example.casestudy.service.post.request.PostSaveRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/posts")
@AllArgsConstructor
public class PostRestController {
    public final PostService postService;
    @PostMapping
    public void createPost(@RequestBody @Valid PostSaveRequest request, Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        postService.createPost(request,userDetails);
    }
}
