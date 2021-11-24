package com.example.kyrsovaia.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Patients {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String number;
    private String email;
    private String data_priema;

    public Patients() {
    }

    public Patients( String name, String number, String email, String data_priema ) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.data_priema = data_priema;
    }

    public String getData_priema() {
        return data_priema;
    }

    public void setData_priema(String data_priema) {
        this.data_priema = data_priema;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

}
