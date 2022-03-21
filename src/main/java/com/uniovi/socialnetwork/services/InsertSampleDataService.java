package com.uniovi.socialnetwork.services;

import com.uniovi.socialnetwork.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Service
public class InsertSampleDataService {
    @Autowired
    private UsersService usersService;

    @Autowired
    private RolesService rolesService;

    @Autowired
    private InvitationsService invitationsService;

    @PostConstruct
    public void init() {
        User user1 = new User("user01@email.com", "User01", "User01");
        user1.setPassword("user01");
        user1.setRole(rolesService.getRoles()[0]);
        User user2 = new User("user02@email.com", "User02", "User02");
        user2.setPassword("user02");
        user2.setRole(rolesService.getRoles()[0]);
        User user3 = new User("user03@email.com", "User03", "User03");
        user3.setPassword("user03");
        user3.setRole(rolesService.getRoles()[0]);
        User user4 = new User("user04@email.com", "User04", "User04");
        user4.setPassword("user04");
        user4.setRole(rolesService.getRoles()[0]);
        User user5 = new User("user05@email.com", "User05", "User05");
        user5.setPassword("user05");
        user5.setRole(rolesService.getRoles()[0]);
        User user6 = new User("user06@email.com", "User06", "User06");
        user6.setPassword("user06");
        user6.setRole(rolesService.getRoles()[0]);

        User admin = new User("admin@email.com", "Admin", "Admin");
        admin.setPassword("admin");
        admin.setRole(rolesService.getRoles()[1]);


        usersService.addUser(user1);
        usersService.addUser(user2);
        usersService.addUser(user3);
        usersService.addUser(user4);
        usersService.addUser(user5);
        usersService.addUser(user6);

        usersService.addUser(admin);

        //amistades
        user1.addFriend(user2);
        user1.addFriend(user3);

        invitationsService.addInvitation(user6, user1);
        invitationsService.addInvitation(user5, user1);
        invitationsService.addInvitation(user4, user1);
    }
}

