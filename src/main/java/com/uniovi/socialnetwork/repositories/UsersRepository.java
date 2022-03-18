package com.uniovi.socialnetwork.repositories;

import com.uniovi.socialnetwork.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface UsersRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);

    @Query("SELECT r FROM User r WHERE NOT r = ?1 AND r.role NOT LIKE ('ROLE_ADMIN')")
    Page<User> findStandardUsers(Pageable pageable,User user);


    @Query("SELECT r FROM User r WHERE r.role NOT LIKE ('ROLE_ADMIN')")
    List<User> findUsers();
}
