package com.uniovi.socialnetwork.controllers;

import com.uniovi.socialnetwork.entities.Post;
import com.uniovi.socialnetwork.entities.User;
import com.uniovi.socialnetwork.services.PostsService;
import com.uniovi.socialnetwork.services.RolesService;
import com.uniovi.socialnetwork.services.SecurityService;
import com.uniovi.socialnetwork.services.UsersService;
import com.uniovi.socialnetwork.validators.SignUpFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.Optional;

@Controller
public class UsersController {

    private Logger log = LoggerFactory.getLogger(LoggerFactory.class);

    @Autowired
    private UsersService usersService;

    @Autowired
    private PostsService postsService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private SignUpFormValidator signUpFormValidator;

    @Autowired
    private RolesService rolesService;

    @RequestMapping("/user/list")
    public String getList(Model model){
        model.addAttribute("usersList", usersService.getUsers());
        return "user/list";
    }

    @RequestMapping("/user/list/update")
    public String updateList(Model model){
        log.debug("Accediendo a /user/list/update");
        model.addAttribute("usersList", usersService.getUsers());
        return "user/list::tableUsers";
    }

    @RequestMapping(value="/user/add")
    public String getUser(Model model){
        model.addAttribute("rolesList", rolesService.getRoles());
        //model.addAttribute("usersList", usersService.getUsers());
        return "user/add";
    }

    @RequestMapping(value="/user/add", method= RequestMethod.POST)
    public String setUser(@ModelAttribute User user){
        usersService.addUser(user);
        return "redirect:/user/list";
    }

    @RequestMapping(value="/user/delete", method= RequestMethod.POST)
    public String deleteUsers(Model model, @RequestParam(value="user",required = false) String users[]){
        for(String id:users){
            User user = usersService.getUser(Long.valueOf(id));
            for(Post post: user.getPosts()){
                postsService.deletePost(post.getId());
            }
            usersService.deleteUser(user.getId());
        }
        model.addAttribute("usersList",usersService.getUsers());
        return "redirect:/user/list";
    }

    @RequestMapping("/user/details/{id}")
    public String getDetail(Model model, @PathVariable Long id){
        model.addAttribute("user", usersService.getUser(id));
        return "user/details";
    }

    @RequestMapping("/user/delete/{id}")
    public String delete(@PathVariable Long id){
        usersService.deleteUser(id);
        return "redirect:/user/list";
    }

    @RequestMapping(value="/user/edit/{id}")
    public String getEdit(Model model, @PathVariable Long id){
        User user = usersService.getUser(id);
        model.addAttribute("user", user);
        return "user/edit";
    }

    @RequestMapping(value="/user/edit/{id}", method=RequestMethod.POST)
    public String setEdit(Model model, @PathVariable Long id, @ModelAttribute User user){
        usersService.updateUser(user);
        return "redirect:/user/details/" + id;
    }

    @RequestMapping(value="/signup", method= RequestMethod.POST)
    public String signup(@Validated User user, BindingResult result){
        signUpFormValidator.validate(user, result);
        if (result.hasErrors()){
            return "signup";
        }
        user.setRole(rolesService.getRoles()[0]);
        usersService.addUser(user);
        securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
        return "redirect:home";
    }

    @RequestMapping(value="/signup", method=RequestMethod.GET)
    public String signup(Model model){
        model.addAttribute("user", new User());
        return "signup";
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(Model model){
        return "login";
    }

    @RequestMapping(value={"/home"}, method= RequestMethod.GET)
    public String home(Model model){
        log.debug("Accediendo a home");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User activeUser = usersService.getUserByEmail(email);
        //model.addAttribute("markList", activeUser.getMarks());
        return "home";
    }
}
