package com.uniovi.socialnetwork.repositories;

import com.uniovi.socialnetwork.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface UsersRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);

    @Query("SELECT r FROM User r WHERE LOWER(r.name) NOT LIKE ('admin')")
    List<User> findUsers();
}
