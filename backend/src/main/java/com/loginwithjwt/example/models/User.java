package com.loginwithjwt.example.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    private String userId;
    private String username;
    private String emailId;

}

