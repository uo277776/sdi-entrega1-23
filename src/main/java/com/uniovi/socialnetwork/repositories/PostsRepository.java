package com.uniovi.socialnetwork.repositories;

import com.uniovi.socialnetwork.entities.Post;
import com.uniovi.socialnetwork.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostsRepository extends CrudRepository<Post, Long> {

    Page<Post> findAll(Pageable pageable);

    @Query("SELECT p FROM Post p WHERE p.owner = ?1 order by p.id ASC")
    Page<Post> findAllByUser(Pageable pageable, User user);

    @Query("SELECT p FROM Post p WHERE p.owner = ?1 order by p.id ASC")
    List<Post> findByUser(User user);
}
