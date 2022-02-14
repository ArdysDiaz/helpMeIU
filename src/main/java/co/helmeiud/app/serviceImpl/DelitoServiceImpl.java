package co.helmeiud.app.serviceImpl;

import co.helmeiud.app.dto.DelitoDto;
import co.helmeiud.app.exception.ErrorDto;
import co.helmeiud.app.exception.NotFoundException;
import co.helmeiud.app.exception.RestException;
import co.helmeiud.app.models.Delito;
import co.helmeiud.app.repository.RepoDelio;
import co.helmeiud.app.service.ServiceDelito;
import co.helmeiud.app.util.ConstUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DelitoServiceImpl implements ServiceDelito {

	
	private RepoDelio repoDelito;
	
	@Transactional(readOnly = true)
	@Override
	public List<DelitoDto> findAll() {
		List<Delito> delitos = repoDelito.findAll();
		List<DelitoDto> delitosDto = new ArrayList<>();
		delitos.stream()
			   .forEach(d -> {
				   DelitoDto delitoDto = new DelitoDto();
				   delitoDto.setId(d.getId());
				   delitoDto.setNombre(d.getNombre());
				   delitoDto.setDescripcion(d.getDescripcion());
				   delitosDto.add(delitoDto);
			   });
		
		return delitosDto;
	}

	@Override
	@Transactional(readOnly = true)
	public Delito findById(Long id) {
		return repoDelito.findById(id).orElse(null);
	}

	@Override
	public Delito save(Delito delito) {
		return repoDelito.save(delito);
	}

	@Override
	@Transactional
	public Delito delete(Long id) throws RestException {
		Optional<Delito> delito = repoDelito.findById(id);
		if(delito.isPresent()) {
			repoDelito.deleteById(id);
			return delito.get();
		}else {
			throw new NotFoundException(ErrorDto.getErrorDto(
					HttpStatus.NOT_FOUND.getReasonPhrase(), 
					ConstUtil.MESSAGE_NOT_FOUND,
					HttpStatus.NOT_FOUND.value())
				);
		}
	}
	
}
