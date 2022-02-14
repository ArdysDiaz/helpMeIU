package co.helmeiud.app.repository;

import co.helmeiud.app.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoRole extends JpaRepository<Role, Long>{

}
