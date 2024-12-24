package com.loginwithjwt.example.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JwtResponse {

    private String username;
    private String jwtToken;
}
