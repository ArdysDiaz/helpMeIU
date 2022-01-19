package com.iudigital.helpmeiu.repository;

import com.iudigital.helpmeiu.models.Delito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepoDelitos extends JpaRepository<Delito, Integer> {
    Optional<Delito> findDelitosByNombre(String nombre);
}
