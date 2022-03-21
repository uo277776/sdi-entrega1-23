package com.uniovi.socialnetwork.controllers;

import com.uniovi.socialnetwork.FileUploadUtil;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
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

    @RequestMapping("post/list/{email}")
    public String getListFriend(Model model, Pageable pageable, Principal principal, @PathVariable String email){
        User user = usersService.getUserByEmail(email);
        User princ = usersService.getUserByEmail(principal.getName());

        if(princ.getFriends().contains(user)){
            Page<Post> posts = postsService.getPostsForUser(pageable, user);

            model.addAttribute("postList", posts);
            model.addAttribute("page", posts);

            return "/post/list";
        }
        else{
            return "user/friends";
        }
    }

    @RequestMapping(value="/post/add", method = RequestMethod.GET)
    public String getPost(Model model, Principal principal){
        Post post = new Post();
        post.setUser(usersService.getUserByEmail(principal.getName()));
        model.addAttribute("post", post);
        return "post/add";
    }

    @RequestMapping(value="/post/add", method = RequestMethod.POST)
    public String setPost(@ModelAttribute Post post, Principal principal, BindingResult result,
                          @RequestParam("image")MultipartFile multipartFile) throws IOException {
        System.out.println("holaaaa");
        createPostFormValidator.validate(post, result);
        if(result.hasErrors()){
            return "post/add";
        }
        post.setDate(new Date());
        post.setUser(usersService.getUserByEmail(principal.getName()));
        post.setHasImage(!multipartFile.isEmpty());

        postsService.addPost(post);

        //Once the post in save we can store the image with the correct name because we have the post's id.
        if (!multipartFile.isEmpty()){
            String fileName = post.getId().toString();
            String uploadDir = "src/main/resources/static/images/postPhotos";
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        }
        return "redirect:/post/list";
    }
}
