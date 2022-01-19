package com.iudigital.helpmeiu.controller;

import com.iudigital.helpmeiu.models.Delito;
import com.iudigital.helpmeiu.serviceImp.ServiceDelitoImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/delitos")
@AllArgsConstructor
public class DelitoController {

    ServiceDelitoImp serviceDelitoImp;

    @GetMapping
    public ResponseEntity<Collection<Delito>> getAllDelitos() {
        return new ResponseEntity<Collection <Delito>>(serviceDelitoImp.getDelitos(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Delito> getDelito(@PathVariable(name = "id") Integer id) {
        return new ResponseEntity<Delito>(serviceDelitoImp.getDelito(id),HttpStatus.OK);
    }

    @PostMapping
    public void saveDelito(@RequestBody Delito delito) {
        serviceDelitoImp.saveDelito(delito);
    }

    @RequestMapping(value = "/{id}",
            produces = "application/json",
            method=RequestMethod.PATCH)
    public void updateDelito(@PathVariable(name = "id") Integer id, @RequestBody Delito delito) {
        serviceDelitoImp.updateDelito(id, delito);
    }

}
