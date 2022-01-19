package com.iudigital.helpmeiu.serviceImp;

import com.iudigital.helpmeiu.models.Delito;
import com.iudigital.helpmeiu.repository.RepoDelitos;
import com.iudigital.helpmeiu.services.ServiceDelito;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service @AllArgsConstructor
public class ServiceDelitoImp implements ServiceDelito {

    private RepoDelitos repoDelitos;

    @Override
    public Collection<Delito> getDelitos() {
        return repoDelitos.findAll();
    }

    @Override
    public Delito getDelito(Integer id) {
        Optional<Delito> delito = repoDelitos.findById(id);
        if (!delito.isPresent())
            throw new IllegalStateException("El delito no existe");
        return delito.get();
    }

    @Override
    public void saveDelito(Delito delito) {
        Optional<Delito> delitoOptional = repoDelitos.findDelitosByNombre(delito.getNombre());
        if (delitoOptional.isPresent())
            throw new IllegalStateException("El delito ya existe");
        repoDelitos.save(delito);
    }

    @Override
    public void updateDelito(Integer id, Delito delito) {
        if (!repoDelitos.existsById(id))
            throw new IllegalStateException("El delito que desea actualizar no existe");
        delito.setId(id);
        repoDelitos.save(delito);
    }
}
