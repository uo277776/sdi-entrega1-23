package com.uniovi.socialnetwork.controllers;

import com.uniovi.socialnetwork.entities.Invitation;
import com.uniovi.socialnetwork.entities.User;
import com.uniovi.socialnetwork.services.InvitationsService;
import com.uniovi.socialnetwork.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class InvitationsController {

    @Autowired
    private InvitationsService invitationsService;

    @Autowired
    private UsersService usersService;


    @RequestMapping("/invitation/send/{id}")
    public String sendInvitation(@PathVariable Long id){
        User receiver = usersService.getUser(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User sender = usersService.getUserByEmail(email);
        //Check if they are already friends or another invitation has been previously sent.
        if (!sender.getFriends().contains(receiver) && !sender.hasSentInvitationToUser(receiver)){
            invitationsService.addInvitation(sender, receiver);
        }
        return "redirect:/user/list";
    }

    @RequestMapping("/invitation/list")
    public String getList(Model model, Pageable pageable, Principal principal){
        String email = principal.getName();
        User user = usersService.getUserByEmail(email);
        Page<Invitation> invitations = invitationsService.getInvitationsForUser(pageable, user);
        model.addAttribute("invitationList", invitations.getContent());
        model.addAttribute("page", invitations);
        return "/invitation/list";
    }

    @RequestMapping("/invitation/accept/{id}")
    public String acceptInvitation(@PathVariable Long id,Principal principal){
        Invitation invitation = invitationsService.getInvitation(id);
        if(invitation.getReceiver().getEmail().equals(principal.getName())){
            usersService.acceptInvitation(invitation.getReceiver(),invitation.getSender());
            invitationsService.deleteAllInvitationsBetween(invitation.getReceiver(),invitation.getSender());
            return "redirect:/invitation/list";
        }
        return "redirect:/error";
    }
}
