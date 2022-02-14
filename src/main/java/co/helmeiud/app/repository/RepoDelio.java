package co.helmeiud.app.repository;

import co.helmeiud.app.models.Delito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoDelio extends JpaRepository<Delito, Long>{
}
