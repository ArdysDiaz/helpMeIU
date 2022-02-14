package co.helmeiud.app.service;

import co.helmeiud.app.dto.DelitoDto;
import co.helmeiud.app.exception.RestException;
import co.helmeiud.app.models.Delito;

import java.util.List;

public interface ServiceDelito {

	public List<DelitoDto> findAll();
	
	public Delito findById(Long id);
	
	public Delito save(Delito delito);
	
	public Delito delete(Long id) throws RestException;
}
