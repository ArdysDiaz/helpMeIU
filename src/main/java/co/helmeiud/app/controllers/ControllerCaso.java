package co.helmeiud.app.controllers;

import co.helmeiud.app.dto.CasoDto;
import co.helmeiud.app.exception.RestException;
import co.helmeiud.app.models.Caso;
import co.helmeiud.app.service.ServiceCaso;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/casos")
@Api(value="/casos", tags= {"Casos"})
@SwaggerDefinition(tags = {
        @Tag(name = "Casos", description = "Gestion API casos")
})
@AllArgsConstructor
public class ControllerCaso {

    private ServiceCaso serviceCaso;


    @ApiOperation(value = "Obtiene todos los casos",
            produces = "application/json",
            httpMethod = "GET")
    @GetMapping
    public ResponseEntity<List<CasoDto>> index() throws RestException {
        List<CasoDto> casoDto = serviceCaso.findAll();
        return ResponseEntity.ok().body(casoDto);
    }

    @ApiOperation(value = "Crea un caso",
            produces = "application/json",
            httpMethod = "POST")
    @PostMapping
    public ResponseEntity<Caso> create(@RequestBody final Caso caso) throws RestException{
        Caso casoSaved = serviceCaso.save(caso);
        return ResponseEntity.status(HttpStatus.CREATED).body(casoSaved);
    }
}
