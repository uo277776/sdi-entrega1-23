package com.uniovi.socialnetwork.validators;

import com.uniovi.socialnetwork.entities.Post;

import com.uniovi.socialnetwork.services.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CreatePostFormValidator implements Validator {

    @Autowired
    private PostsService postsService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Post.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Post post = (Post) target;

        if(post.getTittle().trim().isEmpty()){
            errors.rejectValue("tittle", "Error.post.add.tittle");
        }
    }
}
