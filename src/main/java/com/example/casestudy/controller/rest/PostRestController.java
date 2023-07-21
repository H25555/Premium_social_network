package com.example.casestudy.controller.rest;

import com.example.casestudy.service.UploadService;
import com.example.casestudy.service.post.PostService;
import com.example.casestudy.service.post.request.PostSaveRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostRestController {
    public final PostService postService;
    public final UploadService uploadService;
    @PostMapping("/create")
    public void createPost(@RequestParam("posttextcontent") String text,
                           @RequestParam("fileInput")MultipartFile[] fileInput, Authentication authentication) throws IOException {
        PostSaveRequest request = new PostSaveRequest();
        PostSaveRequest.ContentSaveRequest content = new PostSaveRequest.ContentSaveRequest();
        List<PostSaveRequest.ContentSaveRequest.MediaSaveRequest> media = uploadService.transferFiles(fileInput);
        content.setMedia(media);
        request.setContent(content);
        request.getContent().setText(text);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        postService.createPost(request,userDetails);
    }
}
