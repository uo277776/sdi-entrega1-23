package com.uniovi.socialnetwork.services;

import com.uniovi.socialnetwork.entities.User;
import com.uniovi.socialnetwork.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<User> getUsers(){
        List<User> users = new ArrayList<>();
        usersRepository.findAll().forEach(users::add);
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
        originalUser.setDni(user.getDni());
        originalUser.setName(user.getName());
        originalUser.setLastName(user.getLastName());
        usersRepository.save(originalUser);
    }

    public User getUserByDni(String dni){
        return usersRepository.findByDni(dni);
    }

    public void deleteUser(Long id){
        usersRepository.deleteById(id);
    }

}
