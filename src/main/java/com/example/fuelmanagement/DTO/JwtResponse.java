package com.example.fuelmanagement.DTO;

public class JwtResponse {

    private String jwtToken;

    // Constructor
    public JwtResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    // Getter
    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
