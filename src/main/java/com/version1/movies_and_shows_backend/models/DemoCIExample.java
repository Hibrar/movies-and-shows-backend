package com.version1.movies_and_shows_backend.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class DemoCIExample {
    private String studentname;
    private int age;
    private String course;
    private String uninitialized;

    public String getInfo() {
        String result = null;
        if (studentname.equals("John"))
            result = "Welcome, John!";
        }
        System.out.println("CodeGuru trigger test");
        return result;
    }
}