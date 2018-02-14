package com.mongoDB.vacation.sample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User
{
    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Set<LayoutSettings> layoutSettings;
}
