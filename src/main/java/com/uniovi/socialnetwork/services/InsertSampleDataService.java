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

        //amistades
        user1.addFriend(user2);
        user2.addFriend(user1);
        user1.addFriend(user3);

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

        invitationsService.addInvitation(user6, user1);
        invitationsService.addInvitation(user5, user1);
        invitationsService.addInvitation(user4, user1);

        /*
        Esto lo dejo por orgullo y por sufrimiento

        Post p11 = new Post("Publicación 11", "Primera publicación", user1);
        Post p12 = new Post("Publicación 12", "Segunda publicación", user1);
        Post p13 = new Post("Publicación 13", "Tercera publicación", user1);
        Post p14 = new Post("Publicación 14", "Cuarta publicación", user1);
        Post p15 = new Post("Publicación 15", "Quinta publicación", user1);
        Post p16 = new Post("Publicación 16", "Sexta publicación", user1);
        Post p17 = new Post("Publicación 17", "Septima publicación", user1);
        Post p18 = new Post("Publicación 18", "Octava publicación", user1);
        Post p19 = new Post("Publicación 19", "Novena publicación", user1);
        Post p110 = new Post("Publicación 110", "Decima publicación", user1);

        Post p21 = new Post("Publicación 21", "Primera publicación", user2);
        Post p22 = new Post("Publicación 22", "Segunda publicación", user2);
        Post p23 = new Post("Publicación 23", "Tercera publicación", user2);
        Post p24 = new Post("Publicación 24", "Cuarta publicación", user2);
        Post p25 = new Post("Publicación 25", "Quinta publicación", user2);
        Post p26 = new Post("Publicación 26", "Sexta publicación", user2);
        Post p27 = new Post("Publicación 27", "Septima publicación", user2);
        Post p28 = new Post("Publicación 28", "Octava publicación", user2);
        Post p29 = new Post("Publicación 29", "Novena publicación", user2);
        Post p210 = new Post("Publicación 210", "Decima publicación", user2);

        Post p31 = new Post("Publicación 31", "Primera publicación", user3);
        Post p32 = new Post("Publicación 32", "Segunda publicación", user3);
        Post p33 = new Post("Publicación 33", "Tercera publicación", user3);
        Post p34 = new Post("Publicación 34", "Cuarta publicación", user3);
        Post p35 = new Post("Publicación 35", "Quinta publicación", user3);
        Post p36 = new Post("Publicación 36", "Sexta publicación", user3);
        Post p37 = new Post("Publicación 37", "Septima publicación", user3);
        Post p38 = new Post("Publicación 38", "Octava publicación", user3);
        Post p39 = new Post("Publicación 39", "Novena publicación", user3);
        Post p310 = new Post("Publicación 310", "Decima publicación", user3);

        Post p41 = new Post("Publicación 41", "Primera publicación", user4);
        Post p42 = new Post("Publicación 42", "Segunda publicación", user4);
        Post p43 = new Post("Publicación 43", "Tercera publicación", user4);
        Post p44 = new Post("Publicación 44", "Cuarta publicación", user4);
        Post p45 = new Post("Publicación 45", "Quinta publicación", user4);
        Post p46 = new Post("Publicación 46", "Sexta publicación", user4);
        Post p47 = new Post("Publicación 47", "Septima publicación", user4);
        Post p48 = new Post("Publicación 48", "Octava publicación", user4);
        Post p49 = new Post("Publicación 49", "Novena publicación", user4);
        Post p410 = new Post("Publicación 410", "Decima publicación", user4);

        Post p51 = new Post("Publicación 51", "Primera publicación", user5);
        Post p52 = new Post("Publicación 52", "Segunda publicación", user5);
        Post p53 = new Post("Publicación 53", "Tercera publicación", user5);
        Post p54 = new Post("Publicación 54", "Cuarta publicación", user5);
        Post p55 = new Post("Publicación 55", "Quinta publicación", user5);
        Post p56 = new Post("Publicación 56", "Sexta publicación", user5);
        Post p57 = new Post("Publicación 57", "Septima publicación", user5);
        Post p58 = new Post("Publicación 58", "Octava publicación", user5);
        Post p59 = new Post("Publicación 59", "Novena publicación", user5);
        Post p510 = new Post("Publicación 510", "Decima publicación", user5);

        Post p61 = new Post("Publicación 61", "Primera publicación", user6);
        Post p62 = new Post("Publicación 62", "Segunda publicación", user6);
        Post p63 = new Post("Publicación 63", "Tercera publicación", user6);
        Post p64 = new Post("Publicación 64", "Cuarta publicación", user6);
        Post p65 = new Post("Publicación 65", "Quinta publicación", user6);
        Post p66 = new Post("Publicación 66", "Sexta publicación", user6);
        Post p67 = new Post("Publicación 67", "Septima publicación", user6);
        Post p68 = new Post("Publicación 68", "Octava publicación", user6);
        Post p69 = new Post("Publicación 69", "Novena publicación", user6);
        Post p610 = new Post("Publicación 610", "Decima publicación", user6);

        Post p71 = new Post("Publicación 71", "Primera publicación", user7);
        Post p72 = new Post("Publicación 72", "Segunda publicación", user7);
        Post p73 = new Post("Publicación 73", "Tercera publicación", user7);
        Post p74 = new Post("Publicación 74", "Cuarta publicación", user7);
        Post p75 = new Post("Publicación 75", "Quinta publicación", user7);
        Post p76 = new Post("Publicación 76", "Sexta publicación", user7);
        Post p77 = new Post("Publicación 77", "Septima publicación", user7);
        Post p78 = new Post("Publicación 78", "Octava publicación", user7);
        Post p79 = new Post("Publicación 79", "Novena publicación", user7);
        Post p710 = new Post("Publicación 710", "Decima publicación", user7);

        Post p81 = new Post("Publicación 81", "Primera publicación", user8);
        Post p82 = new Post("Publicación 82", "Segunda publicación", user8);
        Post p83 = new Post("Publicación 83", "Tercera publicación", user8);
        Post p84 = new Post("Publicación 84", "Cuarta publicación", user8);
        Post p85 = new Post("Publicación 85", "Quinta publicación", user8);
        Post p86 = new Post("Publicación 86", "Sexta publicación", user8);
        Post p87 = new Post("Publicación 87", "Septima publicación", user8);
        Post p88 = new Post("Publicación 88", "Octava publicación", user8);
        Post p89 = new Post("Publicación 89", "Novena publicación", user8);
        Post p810 = new Post("Publicación 810", "Decima publicación", user8);

        Post p91 = new Post("Publicación 91", "Primera publicación", user9);
        Post p92 = new Post("Publicación 92", "Segunda publicación", user9);
        Post p93 = new Post("Publicación 93", "Tercera publicación", user9);
        Post p94 = new Post("Publicación 94", "Cuarta publicación", user9);
        Post p95 = new Post("Publicación 95", "Quinta publicación", user9);
        Post p96 = new Post("Publicación 96", "Sexta publicación", user9);
        Post p97 = new Post("Publicación 97", "Septima publicación", user9);
        Post p98 = new Post("Publicación 98", "Octava publicación", user9);
        Post p99 = new Post("Publicación 99", "Novena publicación", user9);
        Post p910 = new Post("Publicación 910", "Decima publicación", user9);

        Post p101 = new Post("Publicación 101", "Primera publicación", user10);
        Post p102 = new Post("Publicación 102", "Segunda publicación", user10);
        Post p103 = new Post("Publicación 103", "Tercera publicación", user10);
        Post p104 = new Post("Publicación 104", "Cuarta publicación", user10);
        Post p105 = new Post("Publicación 105", "Quinta publicación", user10);
        Post p106 = new Post("Publicación 106", "Sexta publicación", user10);
        Post p107 = new Post("Publicación 107", "Septima publicación", user10);
        Post p108 = new Post("Publicación 108", "Octava publicación", user10);
        Post p109 = new Post("Publicación 109", "Novena publicación", user10);
        Post p1010 = new Post("Publicación 1010", "Decima publicación", user10);

        Post p111 = new Post("Publicación 111", "Primera publicación", user11);
        Post p112 = new Post("Publicación 112", "Segunda publicación", user11);
        Post p113 = new Post("Publicación 113", "Tercera publicación", user11);
        Post p114 = new Post("Publicación 114", "Cuarta publicación", user11);
        Post p115 = new Post("Publicación 115", "Quinta publicación", user11);
        Post p116 = new Post("Publicación 116", "Sexta publicación", user11);
        Post p117 = new Post("Publicación 117", "Septima publicación", user11);
        Post p118 = new Post("Publicación 118", "Octava publicación", user11);
        Post p119 = new Post("Publicación 119", "Novena publicación", user11);
        Post p1110 = new Post("Publicación 1110", "Decima publicación", user11);

        Post p121 = new Post("Publicación 121", "Primera publicación", user12);
        Post p122 = new Post("Publicación 122", "Segunda publicación", user12);
        Post p123 = new Post("Publicación 123", "Tercera publicación", user12);
        Post p124 = new Post("Publicación 124", "Cuarta publicación", user12);
        Post p125 = new Post("Publicación 125", "Quinta publicación", user12);
        Post p126 = new Post("Publicación 126", "Sexta publicación", user12);
        Post p127 = new Post("Publicación 127", "Septima publicación", user12);
        Post p128 = new Post("Publicación 128", "Octava publicación", user12);
        Post p129 = new Post("Publicación 129", "Novena publicación", user12);
        Post p1210 = new Post("Publicación 1210", "Decima publicación", user12);

        Post p131 = new Post("Publicación 131", "Primera publicación", user13);
        Post p132 = new Post("Publicación 132", "Segunda publicación", user13);
        Post p133 = new Post("Publicación 133", "Tercera publicación", user13);
        Post p134 = new Post("Publicación 134", "Cuarta publicación", user13);
        Post p135 = new Post("Publicación 135", "Quinta publicación", user13);
        Post p136 = new Post("Publicación 136", "Sexta publicación", user13);
        Post p137 = new Post("Publicación 137", "Septima publicación", user13);
        Post p138 = new Post("Publicación 138", "Octava publicación", user13);
        Post p139 = new Post("Publicación 139", "Novena publicación", user13);
        Post p1310 = new Post("Publicación 1310", "Decima publicación", user13);

        Post p141 = new Post("Publicación 141", "Primera publicación", user14);
        Post p142 = new Post("Publicación 142", "Segunda publicación", user14);
        Post p143 = new Post("Publicación 143", "Tercera publicación", user14);
        Post p144 = new Post("Publicación 144", "Cuarta publicación", user14);
        Post p145 = new Post("Publicación 145", "Quinta publicación", user14);
        Post p146 = new Post("Publicación 146", "Sexta publicación", user14);
        Post p147 = new Post("Publicación 147", "Septima publicación", user14);
        Post p148 = new Post("Publicación 148", "Octava publicación", user14);
        Post p149 = new Post("Publicación 149", "Novena publicación", user14);
        Post p1410 = new Post("Publicación 1410", "Decima publicación", user14);

        Post p151 = new Post("Publicación 151", "Primera publicación", user15);
        Post p152 = new Post("Publicación 152", "Segunda publicación", user15);
        Post p153 = new Post("Publicación 153", "Tercera publicación", user15);
        Post p154 = new Post("Publicación 154", "Cuarta publicación", user15);
        Post p155 = new Post("Publicación 155", "Quinta publicación", user15);
        Post p156 = new Post("Publicación 156", "Sexta publicación", user15);
        Post p157 = new Post("Publicación 157", "Septima publicación", user15);
        Post p158 = new Post("Publicación 158", "Octava publicación", user15);
        Post p159 = new Post("Publicación 159", "Novena publicación", user15);
        Post p1510 = new Post("Publicación 1510", "Decima publicación", user15);
        */


        for(int i=1;i<=10;i++){
            postsService.addPost(new Post("Publicación 1"+i,"Texto Publicación",user1));
        }

        for(int i=1;i<=10;i++){
            postsService.addPost(new Post("Publicación 2"+i,"Texto Publicación",user2));
        }

        for(int i=1;i<=10;i++){
            postsService.addPost(new Post("Publicación 3"+i,"Texto Publicación",user3));
        }

        for(int i=1;i<=10;i++){
            postsService.addPost(new Post("Publicación 4"+i,"Texto Publicación",user4));
        }

        for(int i=1;i<=10;i++){
            postsService.addPost(new Post("Publicación 5"+i,"Texto Publicación",user5));
        }

        for(int i=1;i<=10;i++){
            postsService.addPost(new Post("Publicación 6"+i,"Texto Publicación",user6));
        }

        for(int i=1;i<=10;i++){
            postsService.addPost(new Post("Publicación 7"+i,"Texto Publicación",user7));
        }

        for(int i=1;i<=10;i++){
            postsService.addPost(new Post("Publicación 8"+i,"Texto Publicación",user8));
        }

        for(int i=1;i<=10;i++){
            postsService.addPost(new Post("Publicación 9"+i,"Texto Publicación",user9));
        }

        for(int i=1;i<=10;i++){
            postsService.addPost(new Post("Publicación 10"+i,"Texto Publicación",user10));
        }

        for(int i=1;i<=10;i++){
            postsService.addPost(new Post("Publicación 11"+i,"Texto Publicación",user11));
        }

        for(int i=1;i<=10;i++){
            postsService.addPost(new Post("Publicación 12"+i,"Texto Publicación",user12));
        }

        for(int i=1;i<=10;i++){
            postsService.addPost(new Post("Publicación 13"+i,"Texto Publicación",user13));
        }

        for(int i=1;i<=10;i++){
            postsService.addPost(new Post("Publicación 14"+i,"Texto Publicación",user14));
        }

        for(int i=1;i<=10;i++){
            postsService.addPost(new Post("Publicación 15"+i,"Texto Publicación",user15));
        }



    }
}

