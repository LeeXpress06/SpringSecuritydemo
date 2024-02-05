package com.Camp.SpringSecurity.SpringSecuritydemo.WorkerDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequestDto {
    private String userName;
    private String password;



}
