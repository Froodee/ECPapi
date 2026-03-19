package fr.ecp.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String email;
    private String roles;
    private Integer utilisateurId;
    private String nomUser;
}
