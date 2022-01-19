package com.iudigital.helpmeiu.controller;

import com.iudigital.helpmeiu.dto.UsuarioDto;
import com.iudigital.helpmeiu.models.Usuario;
import com.iudigital.helpmeiu.serviceImp.ServiceUsuarioImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/usuario")
@AllArgsConstructor
public class UsuarioController {

    private ServiceUsuarioImp serviceUsuarioImp;

    @GetMapping()
    public ResponseEntity<List<UsuarioDto>> getUsuarios() {
        return new ResponseEntity<List<UsuarioDto>>(serviceUsuarioImp.getAllUsuarios(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> getUsuarioById(@PathVariable(name = "id") Integer id){
        return new ResponseEntity<UsuarioDto>(serviceUsuarioImp.getUsuarioById(id), HttpStatus.OK);
    }

    @GetMapping("/buscarporusername")
    public ResponseEntity<UsuarioDto> getUsuarioByUsername(@RequestParam(value="username") String username){
        return new ResponseEntity<UsuarioDto>(serviceUsuarioImp.getUsuarioByUsername(username), HttpStatus.OK);
    }

    @PostMapping()
    public void createUsuario(@RequestBody Usuario usuario){
        serviceUsuarioImp.createUsuario(usuario);
    }

    @PatchMapping()
    public void updateUsuario(@RequestBody Usuario usuario){
        serviceUsuarioImp.updateUsuario(usuario);
    }

}
