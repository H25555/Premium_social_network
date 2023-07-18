package com.example.casestudy.controller.rest;

import com.example.casestudy.service.post.PostService;
import com.example.casestudy.service.post.request.PostSaveRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController("/api/posts")
@AllArgsConstructor
public class PostRestController {
    public final PostService postService;
    @PostMapping
    public void createPost(@RequestBody @Valid PostSaveRequest request, Authentication authentication,
                           @RequestParam("mediaFiles")MultipartFile[] multipartFiles){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        for (MultipartFile file : multipartFiles) {
            PostSaveRequest.ContentSaveRequest.MediaSaveRequest media = new PostSaveRequest.ContentSaveRequest.MediaSaveRequest();

            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            media.setUrl(fileName);
            request.getContent().getMedia().add(media);
        }
        postService.createPost(request,userDetails);
    }
}
