package com.example.DemoTool.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenAuthentication {

    public String access_token;
    public String token_type;
    public String refresh_token;
    public int expires_in;
    public String scope;
    public String jti;

}
