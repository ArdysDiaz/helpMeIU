package co.helmeiud.app.controllers;

import co.helmeiud.app.dto.DelitoDto;
import co.helmeiud.app.exception.RestException;
import co.helmeiud.app.models.Delito;
import co.helmeiud.app.service.ServiceDelito;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value="/delitos", tags= {"Delitos"})
@SwaggerDefinition(tags = {
        @Tag(name = "Delitos", description = "Gestion API delitos")
})
@RestController
@RequestMapping("/delitos")
@AllArgsConstructor
public class ControllerDelito {

    private static final Logger log = LoggerFactory.getLogger(ControllerDelito.class);

    private ServiceDelito serviceDelito;

    @ApiOperation(value = "Obtiene todos los delitos",
            response = DelitoDto.class,
            responseContainer = "List",
            produces = "application/json",
            httpMethod = "GET")
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<List<DelitoDto>> getDelitos() {
        log.info("Empoint para obtener la lista de delitos");
        return new ResponseEntity<List<DelitoDto>>(serviceDelito.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Obtiene un delito por su id",
            response = Delito.class,
            produces = "application/json",
            httpMethod = "GET")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/delito/{id}")
    public ResponseEntity<Delito> getDelito(@PathVariable Long id) {return ResponseEntity.ok().body(serviceDelito.findById(id));}

    @ApiOperation(value = "Guarda un delito",
            response = Delito.class,
            produces = "application/json",
            httpMethod = "POST")
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Delito> createDelito(@RequestBody @Valid Delito delito){
        return new ResponseEntity<Delito>(serviceDelito.save(delito), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Elimina un delito por id",
            produces = "application/json",
            httpMethod = "POST")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/delito/{id}")
    public ResponseEntity<Delito> deleteDelito(@PathVariable Long id) throws RestException {
        return new ResponseEntity<Delito>(serviceDelito.delete(id), HttpStatus.OK);
    }

}
