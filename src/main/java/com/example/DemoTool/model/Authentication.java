package com.example.DemoTool.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Authentication {

    private String username;
    private String password;
    private String grant_type;
    private String client_id;
}
