package com.uniovi.socialnetwork.services;

import com.uniovi.socialnetwork.entities.Invitation;
import com.uniovi.socialnetwork.entities.User;
import com.uniovi.socialnetwork.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostConstruct
    public void init(){

    }

    public Page<User> getStandardUsers(Pageable pageable, User user){
        Page<User> users = usersRepository.findStandardUsers(pageable,user);
        return users;
    }

    public List<User> getUsers(){
        List<User> users = new ArrayList<User>();
        usersRepository.findUsers().forEach(users::add);
        return users;
    }

    public User getUser(Long id){
        return usersRepository.findById(id).get();
    }

    public void addUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
    }

    public void updateUser(User user){
        User originalUser = usersRepository.findById(user.getId()).get();
        originalUser.setEmail(user.getEmail());
        originalUser.setName(user.getName());
        originalUser.setLastName(user.getLastName());
        usersRepository.save(originalUser);
    }

    public void acceptInvitation(User user1,User user2){
        user1.addFriend(user2);
        user2.addFriend(user1);
    }

    public User getUserByEmail(String email){
        return usersRepository.findByEmail(email);
    }

    public void deleteUser(Long id){
        usersRepository.deleteById(id);
    }

    public void deleteAll(){usersRepository.deleteAll();}

    public Page<User> searchUsersByNameSurnameAndEmail (Pageable pageable, String searchText, User user) {
        Page<User> users = new PageImpl<User>(new ArrayList<User>());
        searchText = "%"+searchText+"%";
        users = usersRepository.searchUsersByNameSurnameAndEmail(pageable, searchText, user);
        return users;
    }

    public Page<User> getFriendsForUser(Pageable pageable, User user) {
        Page<User> users = new PageImpl<User>(new ArrayList<User>());
        users = usersRepository.getFriendsForUser(pageable, user);
        return users;
    }
}
