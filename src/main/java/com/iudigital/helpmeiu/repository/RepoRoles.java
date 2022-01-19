package com.iudigital.helpmeiu.repository;

import com.iudigital.helpmeiu.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "roles", collectionResourceRel = "roles")
public interface RepoRoles extends JpaRepository<Roles, Integer> {
}
