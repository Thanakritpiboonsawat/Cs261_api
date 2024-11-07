package com.example.crud;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "students")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "eng_name")
    private String engName;
    
    @Column(name = "faculty")
    private String faculty;
    
    @Column(name = "type")
    private String type;
    
    @Column(name = "user_name")
    private String userName;
}