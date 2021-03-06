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

        Post p11 = new Post("Publicaci??n 11", "Primera publicaci??n", user1);
        Post p12 = new Post("Publicaci??n 12", "Segunda publicaci??n", user1);
        Post p13 = new Post("Publicaci??n 13", "Tercera publicaci??n", user1);
        Post p14 = new Post("Publicaci??n 14", "Cuarta publicaci??n", user1);
        Post p15 = new Post("Publicaci??n 15", "Quinta publicaci??n", user1);
        Post p16 = new Post("Publicaci??n 16", "Sexta publicaci??n", user1);
        Post p17 = new Post("Publicaci??n 17", "Septima publicaci??n", user1);
        Post p18 = new Post("Publicaci??n 18", "Octava publicaci??n", user1);
        Post p19 = new Post("Publicaci??n 19", "Novena publicaci??n", user1);
        Post p110 = new Post("Publicaci??n 110", "Decima publicaci??n", user1);

        Post p21 = new Post("Publicaci??n 21", "Primera publicaci??n", user2);
        Post p22 = new Post("Publicaci??n 22", "Segunda publicaci??n", user2);
        Post p23 = new Post("Publicaci??n 23", "Tercera publicaci??n", user2);
        Post p24 = new Post("Publicaci??n 24", "Cuarta publicaci??n", user2);
        Post p25 = new Post("Publicaci??n 25", "Quinta publicaci??n", user2);
        Post p26 = new Post("Publicaci??n 26", "Sexta publicaci??n", user2);
        Post p27 = new Post("Publicaci??n 27", "Septima publicaci??n", user2);
        Post p28 = new Post("Publicaci??n 28", "Octava publicaci??n", user2);
        Post p29 = new Post("Publicaci??n 29", "Novena publicaci??n", user2);
        Post p210 = new Post("Publicaci??n 210", "Decima publicaci??n", user2);

        Post p31 = new Post("Publicaci??n 31", "Primera publicaci??n", user3);
        Post p32 = new Post("Publicaci??n 32", "Segunda publicaci??n", user3);
        Post p33 = new Post("Publicaci??n 33", "Tercera publicaci??n", user3);
        Post p34 = new Post("Publicaci??n 34", "Cuarta publicaci??n", user3);
        Post p35 = new Post("Publicaci??n 35", "Quinta publicaci??n", user3);
        Post p36 = new Post("Publicaci??n 36", "Sexta publicaci??n", user3);
        Post p37 = new Post("Publicaci??n 37", "Septima publicaci??n", user3);
        Post p38 = new Post("Publicaci??n 38", "Octava publicaci??n", user3);
        Post p39 = new Post("Publicaci??n 39", "Novena publicaci??n", user3);
        Post p310 = new Post("Publicaci??n 310", "Decima publicaci??n", user3);

        Post p41 = new Post("Publicaci??n 41", "Primera publicaci??n", user4);
        Post p42 = new Post("Publicaci??n 42", "Segunda publicaci??n", user4);
        Post p43 = new Post("Publicaci??n 43", "Tercera publicaci??n", user4);
        Post p44 = new Post("Publicaci??n 44", "Cuarta publicaci??n", user4);
        Post p45 = new Post("Publicaci??n 45", "Quinta publicaci??n", user4);
        Post p46 = new Post("Publicaci??n 46", "Sexta publicaci??n", user4);
        Post p47 = new Post("Publicaci??n 47", "Septima publicaci??n", user4);
        Post p48 = new Post("Publicaci??n 48", "Octava publicaci??n", user4);
        Post p49 = new Post("Publicaci??n 49", "Novena publicaci??n", user4);
        Post p410 = new Post("Publicaci??n 410", "Decima publicaci??n", user4);

        Post p51 = new Post("Publicaci??n 51", "Primera publicaci??n", user5);
        Post p52 = new Post("Publicaci??n 52", "Segunda publicaci??n", user5);
        Post p53 = new Post("Publicaci??n 53", "Tercera publicaci??n", user5);
        Post p54 = new Post("Publicaci??n 54", "Cuarta publicaci??n", user5);
        Post p55 = new Post("Publicaci??n 55", "Quinta publicaci??n", user5);
        Post p56 = new Post("Publicaci??n 56", "Sexta publicaci??n", user5);
        Post p57 = new Post("Publicaci??n 57", "Septima publicaci??n", user5);
        Post p58 = new Post("Publicaci??n 58", "Octava publicaci??n", user5);
        Post p59 = new Post("Publicaci??n 59", "Novena publicaci??n", user5);
        Post p510 = new Post("Publicaci??n 510", "Decima publicaci??n", user5);

        Post p61 = new Post("Publicaci??n 61", "Primera publicaci??n", user6);
        Post p62 = new Post("Publicaci??n 62", "Segunda publicaci??n", user6);
        Post p63 = new Post("Publicaci??n 63", "Tercera publicaci??n", user6);
        Post p64 = new Post("Publicaci??n 64", "Cuarta publicaci??n", user6);
        Post p65 = new Post("Publicaci??n 65", "Quinta publicaci??n", user6);
        Post p66 = new Post("Publicaci??n 66", "Sexta publicaci??n", user6);
        Post p67 = new Post("Publicaci??n 67", "Septima publicaci??n", user6);
        Post p68 = new Post("Publicaci??n 68", "Octava publicaci??n", user6);
        Post p69 = new Post("Publicaci??n 69", "Novena publicaci??n", user6);
        Post p610 = new Post("Publicaci??n 610", "Decima publicaci??n", user6);

        Post p71 = new Post("Publicaci??n 71", "Primera publicaci??n", user7);
        Post p72 = new Post("Publicaci??n 72", "Segunda publicaci??n", user7);
        Post p73 = new Post("Publicaci??n 73", "Tercera publicaci??n", user7);
        Post p74 = new Post("Publicaci??n 74", "Cuarta publicaci??n", user7);
        Post p75 = new Post("Publicaci??n 75", "Quinta publicaci??n", user7);
        Post p76 = new Post("Publicaci??n 76", "Sexta publicaci??n", user7);
        Post p77 = new Post("Publicaci??n 77", "Septima publicaci??n", user7);
        Post p78 = new Post("Publicaci??n 78", "Octava publicaci??n", user7);
        Post p79 = new Post("Publicaci??n 79", "Novena publicaci??n", user7);
        Post p710 = new Post("Publicaci??n 710", "Decima publicaci??n", user7);

        Post p81 = new Post("Publicaci??n 81", "Primera publicaci??n", user8);
        Post p82 = new Post("Publicaci??n 82", "Segunda publicaci??n", user8);
        Post p83 = new Post("Publicaci??n 83", "Tercera publicaci??n", user8);
        Post p84 = new Post("Publicaci??n 84", "Cuarta publicaci??n", user8);
        Post p85 = new Post("Publicaci??n 85", "Quinta publicaci??n", user8);
        Post p86 = new Post("Publicaci??n 86", "Sexta publicaci??n", user8);
        Post p87 = new Post("Publicaci??n 87", "Septima publicaci??n", user8);
        Post p88 = new Post("Publicaci??n 88", "Octava publicaci??n", user8);
        Post p89 = new Post("Publicaci??n 89", "Novena publicaci??n", user8);
        Post p810 = new Post("Publicaci??n 810", "Decima publicaci??n", user8);

        Post p91 = new Post("Publicaci??n 91", "Primera publicaci??n", user9);
        Post p92 = new Post("Publicaci??n 92", "Segunda publicaci??n", user9);
        Post p93 = new Post("Publicaci??n 93", "Tercera publicaci??n", user9);
        Post p94 = new Post("Publicaci??n 94", "Cuarta publicaci??n", user9);
        Post p95 = new Post("Publicaci??n 95", "Quinta publicaci??n", user9);
        Post p96 = new Post("Publicaci??n 96", "Sexta publicaci??n", user9);
        Post p97 = new Post("Publicaci??n 97", "Septima publicaci??n", user9);
        Post p98 = new Post("Publicaci??n 98", "Octava publicaci??n", user9);
        Post p99 = new Post("Publicaci??n 99", "Novena publicaci??n", user9);
        Post p910 = new Post("Publicaci??n 910", "Decima publicaci??n", user9);

        Post p101 = new Post("Publicaci??n 101", "Primera publicaci??n", user10);
        Post p102 = new Post("Publicaci??n 102", "Segunda publicaci??n", user10);
        Post p103 = new Post("Publicaci??n 103", "Tercera publicaci??n", user10);
        Post p104 = new Post("Publicaci??n 104", "Cuarta publicaci??n", user10);
        Post p105 = new Post("Publicaci??n 105", "Quinta publicaci??n", user10);
        Post p106 = new Post("Publicaci??n 106", "Sexta publicaci??n", user10);
        Post p107 = new Post("Publicaci??n 107", "Septima publicaci??n", user10);
        Post p108 = new Post("Publicaci??n 108", "Octava publicaci??n", user10);
        Post p109 = new Post("Publicaci??n 109", "Novena publicaci??n", user10);
        Post p1010 = new Post("Publicaci??n 1010", "Decima publicaci??n", user10);

        Post p111 = new Post("Publicaci??n 111", "Primera publicaci??n", user11);
        Post p112 = new Post("Publicaci??n 112", "Segunda publicaci??n", user11);
        Post p113 = new Post("Publicaci??n 113", "Tercera publicaci??n", user11);
        Post p114 = new Post("Publicaci??n 114", "Cuarta publicaci??n", user11);
        Post p115 = new Post("Publicaci??n 115", "Quinta publicaci??n", user11);
        Post p116 = new Post("Publicaci??n 116", "Sexta publicaci??n", user11);
        Post p117 = new Post("Publicaci??n 117", "Septima publicaci??n", user11);
        Post p118 = new Post("Publicaci??n 118", "Octava publicaci??n", user11);
        Post p119 = new Post("Publicaci??n 119", "Novena publicaci??n", user11);
        Post p1110 = new Post("Publicaci??n 1110", "Decima publicaci??n", user11);

        Post p121 = new Post("Publicaci??n 121", "Primera publicaci??n", user12);
        Post p122 = new Post("Publicaci??n 122", "Segunda publicaci??n", user12);
        Post p123 = new Post("Publicaci??n 123", "Tercera publicaci??n", user12);
        Post p124 = new Post("Publicaci??n 124", "Cuarta publicaci??n", user12);
        Post p125 = new Post("Publicaci??n 125", "Quinta publicaci??n", user12);
        Post p126 = new Post("Publicaci??n 126", "Sexta publicaci??n", user12);
        Post p127 = new Post("Publicaci??n 127", "Septima publicaci??n", user12);
        Post p128 = new Post("Publicaci??n 128", "Octava publicaci??n", user12);
        Post p129 = new Post("Publicaci??n 129", "Novena publicaci??n", user12);
        Post p1210 = new Post("Publicaci??n 1210", "Decima publicaci??n", user12);

        Post p131 = new Post("Publicaci??n 131", "Primera publicaci??n", user13);
        Post p132 = new Post("Publicaci??n 132", "Segunda publicaci??n", user13);
        Post p133 = new Post("Publicaci??n 133", "Tercera publicaci??n", user13);
        Post p134 = new Post("Publicaci??n 134", "Cuarta publicaci??n", user13);
        Post p135 = new Post("Publicaci??n 135", "Quinta publicaci??n", user13);
        Post p136 = new Post("Publicaci??n 136", "Sexta publicaci??n", user13);
        Post p137 = new Post("Publicaci??n 137", "Septima publicaci??n", user13);
        Post p138 = new Post("Publicaci??n 138", "Octava publicaci??n", user13);
        Post p139 = new Post("Publicaci??n 139", "Novena publicaci??n", user13);
        Post p1310 = new Post("Publicaci??n 1310", "Decima publicaci??n", user13);

        Post p141 = new Post("Publicaci??n 141", "Primera publicaci??n", user14);
        Post p142 = new Post("Publicaci??n 142", "Segunda publicaci??n", user14);
        Post p143 = new Post("Publicaci??n 143", "Tercera publicaci??n", user14);
        Post p144 = new Post("Publicaci??n 144", "Cuarta publicaci??n", user14);
        Post p145 = new Post("Publicaci??n 145", "Quinta publicaci??n", user14);
        Post p146 = new Post("Publicaci??n 146", "Sexta publicaci??n", user14);
        Post p147 = new Post("Publicaci??n 147", "Septima publicaci??n", user14);
        Post p148 = new Post("Publicaci??n 148", "Octava publicaci??n", user14);
        Post p149 = new Post("Publicaci??n 149", "Novena publicaci??n", user14);
        Post p1410 = new Post("Publicaci??n 1410", "Decima publicaci??n", user14);

        Post p151 = new Post("Publicaci??n 151", "Primera publicaci??n", user15);
        Post p152 = new Post("Publicaci??n 152", "Segunda publicaci??n", user15);
        Post p153 = new Post("Publicaci??n 153", "Tercera publicaci??n", user15);
        Post p154 = new Post("Publicaci??n 154", "Cuarta publicaci??n", user15);
        Post p155 = new Post("Publicaci??n 155", "Quinta publicaci??n", user15);
        Post p156 = new Post("Publicaci??n 156", "Sexta publicaci??n", user15);
        Post p157 = new Post("Publicaci??n 157", "Septima publicaci??n", user15);
        Post p158 = new Post("Publicaci??n 158", "Octava publicaci??n", user15);
        Post p159 = new Post("Publicaci??n 159", "Novena publicaci??n", user15);
        Post p1510 = new Post("Publicaci??n 1510", "Decima publicaci??n", user15);
        */


        for(int i=1;i<=10;i++){
            postsService.addPost(new Post("Publicaci??n 1"+i,"Texto Publicaci??n",user1));
        }

        for(int i=1;i<=10;i++){
            postsService.addPost(new Post("Publicaci??n 2"+i,"Texto Publicaci??n",user2));
        }

        for(int i=1;i<=10;i++){
            postsService.addPost(new Post("Publicaci??n 3"+i,"Texto Publicaci??n",user3));
        }

        for(int i=1;i<=10;i++){
            postsService.addPost(new Post("Publicaci??n 4"+i,"Texto Publicaci??n",user4));
        }

        for(int i=1;i<=10;i++){
            postsService.addPost(new Post("Publicaci??n 5"+i,"Texto Publicaci??n",user5));
        }

        for(int i=1;i<=10;i++){
            postsService.addPost(new Post("Publicaci??n 6"+i,"Texto Publicaci??n",user6));
        }

        for(int i=1;i<=10;i++){
            postsService.addPost(new Post("Publicaci??n 7"+i,"Texto Publicaci??n",user7));
        }

        for(int i=1;i<=10;i++){
            postsService.addPost(new Post("Publicaci??n 8"+i,"Texto Publicaci??n",user8));
        }

        for(int i=1;i<=10;i++){
            postsService.addPost(new Post("Publicaci??n 9"+i,"Texto Publicaci??n",user9));
        }

        for(int i=1;i<=10;i++){
            postsService.addPost(new Post("Publicaci??n 10"+i,"Texto Publicaci??n",user10));
        }

        for(int i=1;i<=10;i++){
            postsService.addPost(new Post("Publicaci??n 11"+i,"Texto Publicaci??n",user11));
        }

        for(int i=1;i<=10;i++){
            postsService.addPost(new Post("Publicaci??n 12"+i,"Texto Publicaci??n",user12));
        }

        for(int i=1;i<=10;i++){
            postsService.addPost(new Post("Publicaci??n 13"+i,"Texto Publicaci??n",user13));
        }

        for(int i=1;i<=10;i++){
            postsService.addPost(new Post("Publicaci??n 14"+i,"Texto Publicaci??n",user14));
        }

        for(int i=1;i<=10;i++){
            postsService.addPost(new Post("Publicaci??n 15"+i,"Texto Publicaci??n",user15));
        }



    }
}

