package com.example.getapi.entity;


import lombok.Data;
import java.io.Serializable;

@Data
public class Person implements Serializable {
    private int id;
    private String firstname;
    private String lastname;
    private String address;
}
