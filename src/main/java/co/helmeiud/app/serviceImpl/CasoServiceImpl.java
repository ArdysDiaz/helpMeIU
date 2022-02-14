package co.helmeiud.app.serviceImpl;

import co.helmeiud.app.dto.CasoDto;
import co.helmeiud.app.exception.BadRequestException;
import co.helmeiud.app.exception.ErrorDto;
import co.helmeiud.app.exception.RestException;
import co.helmeiud.app.models.Caso;
import co.helmeiud.app.repository.RepoCaso;
import co.helmeiud.app.service.ServiceCaso;
import co.helmeiud.app.util.ConstUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CasoServiceImpl implements ServiceCaso {

    private RepoCaso repositoryCaso;

    @Transactional(readOnly = true)
    @Override
    public List<CasoDto> findAll() throws RestException {
        List<Caso> casos = repositoryCaso.findAll();

        List<CasoDto> casosDto = new ArrayList<>();
        casos.stream().forEach(c -> {
            CasoDto casoDto = new CasoDto();
            casoDto.setId(c.getId());
            casoDto.setFechaHora(c.getFechaHora());
            casoDto.setLatitud(c.getLatitud());
            casoDto.setLongitud(c.getLongitud());
            casoDto.setVisible(c.getVisible());
            casoDto.setDescripcion(c.getDescripcion());
            casoDto.setUrlMap(c.getUrlMap());
            casoDto.setRmiUrl(c.getRmiUrl());
            casoDto.setUsuarioId(c.getUsuario().getId());
            casoDto.setImage(c.getUsuario().getImage());
            casoDto.setNombre(c.getUsuario().getNombre());
            casosDto.add(casoDto);
        });
        return casosDto;
    }

    @Transactional
    @Override
    public Caso save(Caso caso) throws RestException {
        if(Objects.isNull(caso)) {
            throw new BadRequestException(ErrorDto.getErrorDto(
                    HttpStatus.BAD_REQUEST.getReasonPhrase(),
                    ConstUtil.MALA_PETICION,
                    HttpStatus.BAD_REQUEST.value())
            );
        }
        return repositoryCaso.save(caso);
    }

    @Transactional
    @Override
    public Boolean visible(Boolean visible, Long id) throws RestException {
        return repositoryCaso.setVisible(visible, id);
    }

    @Transactional(readOnly = true)
    @Override
    public Caso findById(Long id) throws RestException {
        BadRequestException badRequestException = new BadRequestException(ErrorDto.getErrorDto(
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ConstUtil.MALA_PETICION,
                HttpStatus.BAD_REQUEST.value()));
        return repositoryCaso.findById(id).orElseThrow(()-> badRequestException);
    }
}
