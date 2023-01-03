package com.gihub.smkjke.tests.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class User {

    private String name;
    private String job;
//    private String email;
//    private String password;

}
