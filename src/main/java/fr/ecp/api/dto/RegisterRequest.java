package fr.ecp.api.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private String nomUser;
    private String adrUser;
    private String cpUser;
    private String villeUser;
    private String numUser;
    private String role; // CLIENT, ADMIN, EMPLOYE
}
