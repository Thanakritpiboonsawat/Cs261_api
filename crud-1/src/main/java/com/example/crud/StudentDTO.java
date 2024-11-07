package com.example.crud;

import lombok.Data;

@Data
public class StudentDTO {
    private Long id;
    private String email;
    private String engName;
    private String faculty;
    private String type;
    private String userName;
}
