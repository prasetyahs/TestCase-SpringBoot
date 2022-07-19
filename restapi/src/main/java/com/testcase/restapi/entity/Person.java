package com.testcase.restapi.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Table(name = "person")
@Entity
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "First Name Is Required")
    private String firstname;

    @NotEmpty(message = "Last Name Is Required")
    private String lastname;

    @NotEmpty(message = "Address Is Required")
    private String address;
}
