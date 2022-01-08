package com.iudigital.helpmeiu.repository;

import com.iudigital.helpmeiu.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "usuario")
public interface RepoUsuario extends JpaRepository<Usuario, Long> {
}
