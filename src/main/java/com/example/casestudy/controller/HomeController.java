package com.example.casestudy.controller;

import com.example.casestudy.model.User;
import com.example.casestudy.repository.UserRepository;
import com.example.casestudy.service.post.request.PostSaveRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("/index")
@AllArgsConstructor
public class HomeController {
    private final UserRepository userRepository;
    @GetMapping
    public ModelAndView showHomePage(Authentication authentication){
        ModelAndView view = new ModelAndView("index");
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByUserName(userDetails.getUsername());
        view.addObject("user", user);
        view.addObject("newPost", new PostSaveRequest());
        return view;
    }
}
