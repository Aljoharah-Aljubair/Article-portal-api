package com.artical.portal.api.dto;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private String accessToken;
    private String tokenType="Bearer ";
    private String message ="ValidToken";

    public AuthResponseDTO(String accessToken){
        this.accessToken=accessToken;

    }
}
