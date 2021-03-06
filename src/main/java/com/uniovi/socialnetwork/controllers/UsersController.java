package com.uniovi.socialnetwork.controllers;

import com.uniovi.socialnetwork.entities.Post;
import com.uniovi.socialnetwork.entities.User;
import com.uniovi.socialnetwork.services.*;
import com.uniovi.socialnetwork.validators.SignUpFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class UsersController {


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
    public String getList(Model model, Principal principal, Pageable pageable, @RequestParam(value= "", required = false)String searchText){
        String email = principal.getName();
        User user = usersService.getUserByEmail(email);

        if(user.getRole().equals("ROLE_ADMIN")){
            model.addAttribute("usersList", usersService.getUsers());

        }else {
            Page<User> users;
            if(searchText != null && !searchText.isBlank())
                users = usersService.searchUsersByNameSurnameAndEmail(pageable,searchText,user);
            else
                users = usersService.getStandardUsers(pageable,user);

            model.addAttribute("usersList",users.getContent());
            model.addAttribute("page",users);
        }
        return "user/list";
    }

    @RequestMapping("/user/list/update")
    public String updateList(Model model,Principal principal,Pageable pageable){
        String email = principal.getName();
        User user = usersService.getUserByEmail(email);
        if(user.getRole().equals("ROLE_ADMIN")){
            model.addAttribute("usersList", usersService.getUsers());
        }else {
            model.addAttribute("usersList", usersService.getStandardUsers(pageable,user));
        }
        return "user/list::tableUsers";
    }

    @RequestMapping("/user/friends")
    public String getFriendsList(Model model, Pageable pageable, Principal principal){
        String email = principal.getName();
        User user = usersService.getUserByEmail(email);
        Page<User> friends ;
        friends = usersService.getFriendsForUser(pageable, user);
        model.addAttribute("friendsList", friends.getContent());
        model.addAttribute("page", friends);
        return "/user/friends";
    }

    @RequestMapping(value="/user/delete", method= RequestMethod.POST)
    public String deleteUsers(Model model, @RequestParam(value="user",required = false) String[] users){
        for(String id:users){
            User user = usersService.getUser(Long.valueOf(id));
            if(user.getRole().equals("ROLE_STANDARD"))
                usersService.deleteUser(user.getId());
        }
        model.addAttribute("usersList",usersService.getUsers());
        return "redirect:/user/list";
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
        return "redirect:/user/list";
    }

    @RequestMapping(value="/signup", method=RequestMethod.GET)
    public String signup(Model model){
        model.addAttribute("user", new User());
        return "signup";
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(Model model, String error){
        if (error != null){
            model.addAttribute("error", "Error.login");
        }
        return "login";
    }

}
