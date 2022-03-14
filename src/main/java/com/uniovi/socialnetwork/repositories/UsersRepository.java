package com.uniovi.socialnetwork.repositories;

import com.uniovi.socialnetwork.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
