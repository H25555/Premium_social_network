package com.example.casestudy.controller;

import com.example.casestudy.service.post.request.PostSaveRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("/index")
@AllArgsConstructor
public class HomeController {
    @GetMapping
    public ModelAndView showHomePage(){
        ModelAndView view = new ModelAndView("index");
        view.addObject("newPost", new PostSaveRequest());
        return view;
    }
}
