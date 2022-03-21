package com.uniovi.socialnetwork.controllers;

import com.uniovi.socialnetwork.entities.Post;
import com.uniovi.socialnetwork.entities.User;
import com.uniovi.socialnetwork.services.PostsService;
import com.uniovi.socialnetwork.services.UsersService;
import com.uniovi.socialnetwork.validators.CreatePostFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Date;

@Controller
public class PostsController {

    @Autowired
    private PostsService postsService;

    @Autowired
    private UsersService usersService;
    
    @Autowired
    private CreatePostFormValidator createPostFormValidator;

    @RequestMapping("post/list")
    public String getList(Model model, Pageable pageable, Principal principal){
        String email = principal.getName();
        User user = usersService.getUserByEmail(email);

        Page<Post> posts = postsService.getPostsForUser(pageable, user);

        model.addAttribute("postList", posts);
        model.addAttribute("page", posts);

        return "/post/list";
    }

    @RequestMapping(value="/post/add")
    public String getPost(Model model, Principal principal){
        Post post = new Post();
        post.setUser(usersService.getUserByEmail(principal.getName()));
        model.addAttribute("post", post);
        return "post/add";
    }

    @RequestMapping(value="/post/add", method = RequestMethod.POST)
    public String setPost(@ModelAttribute Post post, Principal principal, BindingResult result){
        createPostFormValidator.validate(post, result);
        if(result.hasErrors()){
            return "post/add";
        }
        post.setDate(new Date());
        post.setUser(usersService.getUserByEmail(principal.getName()));
        System.out.println(post.getUser());
        postsService.addPost(post);
        return "redirect:/post/list";
    }
}
