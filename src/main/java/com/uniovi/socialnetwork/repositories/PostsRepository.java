package com.uniovi.socialnetwork.repositories;

import com.uniovi.socialnetwork.entities.Post;
import com.uniovi.socialnetwork.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface PostsRepository extends CrudRepository<Post, Long> {
}
