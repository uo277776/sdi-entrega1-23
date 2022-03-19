package com.uniovi.socialnetwork.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="post")
public class Post {

     @Id
     @GeneratedValue
     private Long id;

     private String tittle;
     private String text;
     private Date date;

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
