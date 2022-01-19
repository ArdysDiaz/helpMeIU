package com.iudigital.helpmeiu.services;

import com.iudigital.helpmeiu.dto.UsuarioDto;
import com.iudigital.helpmeiu.models.Usuario;

import java.util.List;

public interface ServiceUsuario {

    List<UsuarioDto> getAllUsuarios();

    UsuarioDto getUsuarioById(long id);

    UsuarioDto getUsuarioByUsername(String username);

    void createUsuario(Usuario user);

    void updateUsuario(Usuario user);

    void cambioEstado(long id);

}
