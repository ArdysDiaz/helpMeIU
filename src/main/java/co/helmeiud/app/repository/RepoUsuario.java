package co.helmeiud.app.repository;


import co.helmeiud.app.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoUsuario extends JpaRepository<Usuario, Long>{

	Usuario findByUsername(String email);

}
