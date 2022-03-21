package com.uniovi.socialnetwork.services;

import com.uniovi.socialnetwork.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


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
        User user7 = new User("user07@email.com", "User07", "User07");
        user7.setPassword("user07");
        user7.setRole(rolesService.getRoles()[0]);
        User user8 = new User("user08@email.com", "User08", "User08");
        user8.setPassword("user08");
        user8.setRole(rolesService.getRoles()[0]);
        User user9 = new User("user09@email.com", "User09", "User09");
        user9.setPassword("user09");
        user9.setRole(rolesService.getRoles()[0]);
        User user10 = new User("user10@email.com", "User10", "User10");
        user10.setPassword("user10");
        user10.setRole(rolesService.getRoles()[0]);
        User user11 = new User("user11@email.com", "User11", "User11");
        user11.setPassword("user11");
        user11.setRole(rolesService.getRoles()[0]);
        User user12 = new User("user12@email.com", "User12", "User12");
        user12.setPassword("user12");
        user12.setRole(rolesService.getRoles()[0]);

        User user13 = new User("user13@email.com", "User13", "User13");
        user13.setPassword("user13");
        user13.setRole(rolesService.getRoles()[0]);
        User user14 = new User("user14@email.com", "User14", "User14");
        user14.setPassword("user14");
        user14.setRole(rolesService.getRoles()[0]);
        User user15 = new User("user15@email.com", "User15", "User15");
        user15.setPassword("user15");
        user15.setRole(rolesService.getRoles()[0]);


        User admin = new User("admin@email.com", "Admin", "Admin");
        admin.setPassword("admin");
        admin.setRole(rolesService.getRoles()[1]);


        usersService.addUser(user1);
        usersService.addUser(user2);
        usersService.addUser(user3);
        usersService.addUser(user4);
        usersService.addUser(user5);
        usersService.addUser(user6);
        usersService.addUser(user7);
        usersService.addUser(user8);
        usersService.addUser(user9);
        usersService.addUser(user10);
        usersService.addUser(user11);
        usersService.addUser(user12);
        usersService.addUser(user13);
        usersService.addUser(user14);
        usersService.addUser(user15);

        usersService.addUser(admin);

        invitationsService.addInvitation(user10, user5);
        invitationsService.addInvitation(user7, user5);
        invitationsService.addInvitation(user12, user7);

    }
}

