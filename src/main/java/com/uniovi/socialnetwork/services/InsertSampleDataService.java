package com.uniovi.socialnetwork.services;

import com.uniovi.socialnetwork.entities.Post;
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

    @Autowired
    private PostsService postsService;

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

        Post p11 = new Post("Publicación 1", "Primera publicación", user1);
        Post p12 = new Post("Publicación 2", "Segunda publicación", user1);
        Post p13 = new Post("Publicación 3", "Tercera publicación", user1);
        Post p14 = new Post("Publicación 4", "Cuarta publicación", user1);
        Post p15 = new Post("Publicación 5", "Quinta publicación", user1);
        Post p21 = new Post("Pub1", "Pub del user2", user2);

        postsService.addPost(p11);
        postsService.addPost(p12);
        postsService.addPost(p13);
        postsService.addPost(p14);
        postsService.addPost(p15);
        postsService.addPost(p21);

    }
}

