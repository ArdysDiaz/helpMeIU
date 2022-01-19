package com.iudigital.helpmeiu.serviceImp;

import com.iudigital.helpmeiu.dto.UsuarioDto;
import com.iudigital.helpmeiu.models.Usuario;
import com.iudigital.helpmeiu.repository.RepoUsuario;
import com.iudigital.helpmeiu.services.ServiceUsuario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServiceUsuarioImp implements ServiceUsuario {

    private RepoUsuario repoUsuario;

    //listar todos los usuarios
    @Override
    public List<UsuarioDto> getAllUsuarios() {
        List<UsuarioDto> listUsuariosDto = new ArrayList<>();
        List<Usuario> usuarios = repoUsuario.findAll();
        usuarios.stream().forEach(usuario -> {
            UsuarioDto usuarioDto = transformUsuario(usuario);
            listUsuariosDto.add(usuarioDto);
        });
        return listUsuariosDto;
    }

    //buscar usuario por el id
    @Override
    public UsuarioDto getUsuarioById(long id) {
        Optional<Usuario> user = repoUsuario.findById(id);
        if (!user.isPresent()) {
            throw new IllegalStateException("Error al buscar el usuario por Id");
        }
        return transformUsuario(user.get());
    }

    //buscar usuario por el username
    @Override
    public UsuarioDto getUsuarioByUsername(String username) {
        Optional<Usuario> user = repoUsuario.findUsuarioByusername(username);
        if (!user.isPresent()) {
            throw new IllegalStateException("Error al buscar el usuario por username");
        }
        return transformUsuario(user.get());
    }

    //crear usuario
    @Override
    @Transactional
    public void createUsuario(Usuario user) {
        Optional<Usuario> usuario = repoUsuario.findUsuarioByusername(user.getUsername());
        if (usuario.isPresent())
            throw new IllegalStateException("El usuario ya se encuentra registrado");
        repoUsuario.save(user);
    }

    //actualizar usuario
    @Transactional
    @Override
    public void updateUsuario(Usuario user) {
        Optional<Usuario> usuario = repoUsuario.findById(user.getId());
        if (!usuario.isPresent()) {
            throw new IllegalStateException("El Id del usuario a actualizar no existe");
        }
        repoUsuario.save(user);
    }

    /*
        No es recomendable elinimar usuarios en la base de datos
        se cambia el estado de activo a inactivo
     */
    @Override
    public void cambioEstado(long id) {
        Optional<Usuario> usuario = repoUsuario.findById(id);
        if (!usuario.isPresent()) {
            throw new IllegalStateException("Error al buscar el usuario por username");
        }
        usuario.get().setEnabled(usuario.get().isEnabled() ? false : true);
        repoUsuario.save(usuario.get());
    }

    //transforma de un usuario a un usuarioDto
    private UsuarioDto transformUsuario(Usuario user) {
        return new UsuarioDto(user.getId(),
                user.getUsername(),
                user.getNombre(),
                user.getApellido(),
                user.getFecha_nacimiento(),
                user.isEnabled(),
                user.isRed_social(),
                user.getImage(),
                user.getRoles_usuarios());
    }
}
