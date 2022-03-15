package com.uniovi.socialnetwork.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue
    private long id;
    @Column(unique = true)
    private String email;
    private String name;
    private String lastName;

    private String password;

    @Transient //propiedad que no se almacena en la tabla.
    private String passwordConfirm;

    private String role;

    @OneToMany(mappedBy = "owner")
    private Set<Post> posts;



    public User(String email, String name, String lastName){
        super();
        this.email = email;
        this.name = name;
        this.lastName = lastName;
    }

    public User(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName(){
        return this.name + " " + this.lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getRole(){return this.role;}

    public void setRole(String role){this.role = role;}

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}
