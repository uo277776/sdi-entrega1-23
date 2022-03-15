package com.uniovi.socialnetwork.services;

import com.uniovi.socialnetwork.repositories.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostsService {

    @Autowired
    private PostsRepository postsRepository;

    public void deletePost(Long id){
        postsRepository.deleteById(id);
    }


}
