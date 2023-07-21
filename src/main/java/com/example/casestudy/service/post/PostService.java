package com.example.casestudy.service.post;

import com.example.casestudy.model.Post;
import com.example.casestudy.model.User;
import com.example.casestudy.repository.PostRepository;
import com.example.casestudy.repository.UserRepository;
import com.example.casestudy.service.post.request.PostSaveRequest;
import com.example.casestudy.utils.AppUtils;
import com.example.casestudy.utils.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public List<Post> getAllPost() {
        return postRepository.findAll();
    }
    public Post createPost(PostSaveRequest request, UserDetails userDetails){
        Post post = AppUtils.mapper.map(request, Post.class);
        String userName = userDetails.getUsername();
        User user = userRepository.findByEmailOrUserNameOrPhoneNumber(userName,userName,userName);
        post.setUser(user);
        post.setLikeCount(0);
        post.setCreate_date(LocalDateTime.now());
        for (var item: post.getContent().getMedia()){
            item.setContent(post.getContent());
        }
        Post postCreate = postRepository.save(post);
        return postCreate;
    }
}