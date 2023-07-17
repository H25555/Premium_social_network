package com.example.casestudy.service.post;

import com.example.casestudy.model.Post;
import com.example.casestudy.model.User;
import com.example.casestudy.repository.PostRepository;
import com.example.casestudy.repository.UserRepository;
import com.example.casestudy.service.post.request.PostSaveRequest;
import com.example.casestudy.ulti.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    public void createPost(PostSaveRequest request, UserDetails userDetails){
       Post post = AppUtils.mapper.map(request, Post.class);
       String userName = userDetails.getUsername();
       User user = userRepository.findByEmailOrUserNameOrPhoneNumber(userName,userName,userName);
       post.setUser(user);
       post.setCreate_date(LocalDateTime.now());
       postRepository.save(post);
    }
}
