package com.iudigital.helpmeiu.dto;

import com.iudigital.helpmeiu.models.Roles;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter @AllArgsConstructor(access = AccessLevel.PUBLIC)
public class UsuarioDto {
    private long id;
    private String username;
    private String nombre;
    private String apellido;
    private LocalDate fecha_nacimiento;
    private boolean enabled;
    private boolean red_social;
    private String image;
    private List<Roles> roles_usuarios;
}
