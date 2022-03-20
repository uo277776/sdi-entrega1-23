package com.uniovi.socialnetwork.services;

import com.uniovi.socialnetwork.entities.Invitation;
import com.uniovi.socialnetwork.entities.User;
import com.uniovi.socialnetwork.repositories.InvitationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class InvitationsService {

    @Autowired
    private InvitationsRepository invitationsRepository;

    public void addInvitation(User sender, User receiver){
        invitationsRepository.save(new Invitation(sender, receiver));
    }

    public void deleteInvitation(Long id){
        invitationsRepository.deleteById(id);
    }

    public Page<Invitation> getInvitationsForUser(Pageable pageable, User user){
        Page<Invitation> invitations = new PageImpl<Invitation>(new LinkedList<>());
        invitations = invitationsRepository.findAllByUser(pageable, user);
        return invitations;
    }

    public Invitation getInvitation(Long id){
        return invitationsRepository.findById(id).get();
    }

    public void deleteAllInvitationsBetween(User user1,User user2){
        List<Invitation> invitation = invitationsRepository.deleteInvitationsBetween(user1,user2);
        for(Invitation inv : invitation){
            invitationsRepository.deleteById(inv.getId());
        }
    }
}
