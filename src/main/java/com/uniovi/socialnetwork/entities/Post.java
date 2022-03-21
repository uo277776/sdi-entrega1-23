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

    public Post(String tittle, String text, User owner) {
        this.tittle = tittle;
        this.text = text;
        this.date = new Date();
        this.owner = owner;
    }

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

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
