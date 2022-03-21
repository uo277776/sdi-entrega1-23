package com.uniovi.socialnetwork.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="post")
public class Post {

     @Id
     @GeneratedValue
     private Long id;

     private String tittle;
     private String text;
     private Date date;
     private int likes;
     private boolean hasImage;

    @ManyToOne
    @JoinColumn(name = "owner")
    private User owner;

    @ManyToMany
    private Set<User> recommendedBy = new HashSet<>();

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

    public boolean hasImage(){
        return this.hasImage;
    }

    public void setHasImage(boolean image){
        this.hasImage = image;
    }

    public String getImagePath(){
        if (hasImage){
            return "/images/postPhotos/" + id;
        }
        return null;
    }
    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void addLike(){
        this.likes += 1;
    }

    public void addRecommended(User user){
        recommendedBy.add(user);
    }

    public boolean recommendedByUser(String email){
        for(User user: recommendedBy){
            if (user.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

}
