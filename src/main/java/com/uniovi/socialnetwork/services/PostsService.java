package com.uniovi.socialnetwork.services;

import com.uniovi.socialnetwork.entities.Post;
import com.uniovi.socialnetwork.entities.User;
import com.uniovi.socialnetwork.repositories.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class PostsService {

    @Autowired
    private PostsRepository postsRepository;

    public Page<Post> getPosts(Pageable pageable){
        Page<Post> posts = postsRepository.findAll(pageable);
        return posts;
    }

    public Page<Post> getPostsForUser(Pageable pageable, User user){
        Page<Post> posts = new PageImpl<>(new LinkedList<>());
        posts = postsRepository.findAllByUser(pageable, user);

        return posts;
    }

    public void AddPost(Post post){
        postsRepository.save(post);
    }

    public void deletePost(Long id){
        postsRepository.deleteById(id);
    }


}
