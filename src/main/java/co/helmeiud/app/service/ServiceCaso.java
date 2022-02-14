package co.helmeiud.app.service;

import co.helmeiud.app.dto.CasoDto;
import co.helmeiud.app.exception.RestException;
import co.helmeiud.app.models.Caso;

import java.util.List;

public interface ServiceCaso {
    public List<CasoDto> findAll() throws RestException;

    public Caso save(Caso caso) throws RestException;

    public Boolean visible(Boolean visible, Long id) throws RestException;

    public Caso findById(Long id) throws RestException;
}
