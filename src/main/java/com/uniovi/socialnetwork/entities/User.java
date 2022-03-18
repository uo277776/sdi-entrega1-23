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
    private String role;

    private String password;
    @Transient //propiedad que no se almacena en la tabla.
    private String passwordConfirm;


    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private Set<Invitation> sendedInvitations;
    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    private Set<Invitation> receivedInvitations;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<Post> posts;

    @ManyToMany
    @JoinTable(
            name = "Friendship",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "FRIEND_ID")}
    )
    private Set<User> friends;

    @ManyToMany(mappedBy = "friends")
    private Set<User> friends_of;

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

    public Set<Invitation> getReceivedInvitations(){
        return this.receivedInvitations;
    }

    public Set<User> getFriends(){
        return this.friends;
    }



    public boolean receivedInvitationFromUser(String email){
        for (Invitation invitation: receivedInvitations){
            if (invitation.getSender().getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

    public boolean hasSentInvitationToUser(User user){
        for (Invitation invitation: sendedInvitations){
            if (invitation.getReceiver().equals(user)){
                return true;
            }
        }
        return false;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }


    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public Set<User> getFriends_of() {
        return friends_of;
    }

    public void setFriends_of(Set<User> friends_of) {
        this.friends_of = friends_of;
    }

    public void addFriend(User user){
        friends.add(user);
        user.getFriends_of().add(this);
    }

}
