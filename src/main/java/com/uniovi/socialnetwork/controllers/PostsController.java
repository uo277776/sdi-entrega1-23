package com.uniovi.socialnetwork.controllers;

import com.uniovi.socialnetwork.entities.Post;
import com.uniovi.socialnetwork.entities.User;
import com.uniovi.socialnetwork.services.PostsService;
import com.uniovi.socialnetwork.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class PostsController {

    @Autowired
    private PostsService postsService;

    @Autowired
    private UsersService usersService;

    @RequestMapping("post/list")
    public String getList(Model model, Pageable pageable, Principal principal){
        String email = principal.getName();

        User user = usersService.getUserByEmail(email);
        Page<Post> posts = postsService.getPostsForUser(pageable, user);

        model.addAttribute("postList", posts);
        model.addAttribute("page", posts);

        return "/post/list";
    }
}
