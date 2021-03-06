package com.iudigital.helpmeiu.repository;

import com.iudigital.helpmeiu.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepoUsuario extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findUsuarioByusername(String username);
}
