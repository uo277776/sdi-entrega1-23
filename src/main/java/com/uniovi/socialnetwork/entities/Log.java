package com.uniovi.socialnetwork.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
public class Log {

    @Id
    @GeneratedValue
    private Long id;

    private String type;

    private Timestamp fecha;

    private String message;

    public Log(String type,String message){
        this.type = type;
        fecha= Timestamp.valueOf(LocalDateTime.now());
        this.message = message;
    }

    public Log() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
