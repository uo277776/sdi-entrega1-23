package com.uniovi.socialnetwork.entities;

import javax.persistence.*;

@Entity
public class Post {

     @Id
     @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner")
    private User owner;

    public Post(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return owner;
    }

    public void setUser(User user) {
        this.owner = user;
    }
}
