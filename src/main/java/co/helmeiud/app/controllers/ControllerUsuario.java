package co.helmeiud.app.controllers;

import co.helmeiud.app.dto.UsuarioDto;
import co.helmeiud.app.exception.ErrorDto;
import co.helmeiud.app.exception.NotFoundException;
import co.helmeiud.app.exception.RestException;
import co.helmeiud.app.models.Usuario;
import co.helmeiud.app.service.ServiceEmail;
import co.helmeiud.app.service.ServiceUsuario;
import co.helmeiud.app.util.ConstUtil;
import co.helmeiud.app.util.Helper;
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
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")
@Api(value = "/usuarios", tags = {"Usuarios"})
@SwaggerDefinition(tags = {
        @Tag(name = "Usuarios", description = "Gestion API Usuarios")
})
@AllArgsConstructor
public class ControllerUsuario {
    // TODO: IMPLEMENTAR SPRING SECURITY
    private static final Logger log = LoggerFactory.getLogger(ControllerUsuario.class);
    
    private ServiceUsuario serviceUsuario;

    private ServiceEmail serviceEmail;

    @ApiOperation(value = "Obtiene todos los usuarios",
            produces = "application/json",
            httpMethod = "GET")
    @GetMapping
    public ResponseEntity<List<UsuarioDto>> getUsuarios() throws RestException {
        return ResponseEntity.ok().body(serviceUsuario.listUsers());
    }

    @ApiOperation(value = "Obtiene un usuario por Id",
            response = Usuario.class,
            produces = "application/json",
            httpMethod = "GET")
    @GetMapping("/usuario/{id}")
    public ResponseEntity<UsuarioDto> getUsuario(@PathVariable Long id) throws RestException {
        Usuario usuario = serviceUsuario.listUser(id);
        return ResponseEntity.ok().body(Helper.getMapValuesClient(usuario));
    }

    @ApiOperation(value = "Da de alta a un usuario en la app",
            response = Usuario.class,
            produces = "application/json",
            httpMethod = "POST")
    @PostMapping("/signup")
    public ResponseEntity<UsuarioDto> createUsuario(@RequestBody @Valid Usuario usuario) throws RestException{
        Usuario usuarioSaved = serviceUsuario.saveUser(usuario);

        if(Objects.nonNull(usuarioSaved)) {
            String mess = "Su usuario "+ usuarioSaved.getUsername() +
                    " y contraseña " + usuarioSaved.getPassword();
            String to = usuarioSaved.getUsername();
            String subj = ConstUtil.ASUNTO_MESSAGE;
            boolean sent = serviceEmail.sendEmail(mess, to, subj);
            if(!sent) {
                log.error("Error al enviar el correo");
                throw new NotFoundException(ErrorDto.getErrorDto(
                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                        ConstUtil.MESSAGE_NOT_FOUND,
                        HttpStatus.NOT_FOUND.value()
                )
                );
            }
            log.info("Informacion enviada al correo");
        }
        return new ResponseEntity<UsuarioDto>(Helper.getMapValuesClient(usuarioSaved), HttpStatus.CREATED);
    }

    @PostMapping("/upload/{email}")
    public ResponseEntity<?> uploadImageUsuario(
            @RequestParam("image") MultipartFile image,
            @PathVariable String email) throws RestException{
        Map<String, Object> response = new HashMap<>();
        Usuario usuario = serviceUsuario.listByUsername(email);
        if(!image.isEmpty()) {
            String nombreImage = UUID.randomUUID().toString()
                    .concat("_")
                    .concat(image.getOriginalFilename().replace(" ", ""));
            Path path = Paths.get("uploads").resolve(nombreImage).toAbsolutePath();

            try {
                Files.copy(image.getInputStream(), path);
            } catch (IOException e) {
                response.put("Error IO", e.getMessage().concat(e.getCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            String imageBD = usuario.getImage();

            if(Objects.nonNull(imageBD) && imageBD.length() > 0
                    && !imageBD.startsWith("http")) {
                Path pathAntes = Paths.get("uploads").resolve(imageBD).toAbsolutePath();

                File imageFileAntes = pathAntes.toFile();
                if(imageFileAntes.exists() && imageFileAntes.canRead()) {
                    imageFileAntes.delete();
                }
            }
            usuario.setImage(nombreImage);
            serviceUsuario.updateUser(usuario);
            response.put("usuario", usuario);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Actualiza un usuario",
            response = Usuario.class,
            produces = "application/json",
            httpMethod = "PUT")
    @PutMapping("/usuario")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable String username,
                                          @RequestBody Usuario usuario) throws RestException{
        Usuario usuarioBD = serviceUsuario.listByUsername(username);
        if(Objects.isNull(usuarioBD)) {
            throw new NotFoundException(ErrorDto.getErrorDto(
                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                    ConstUtil.MESSAGE_NOT_FOUND,
                    HttpStatus.NOT_FOUND.value()
            )
            );
        }
        usuarioBD.setNombre(usuario.getNombre());
        usuarioBD.setApellido(usuario.getApellido());
        usuarioBD.setFechaNacimiento(usuario.getFechaNacimiento());
        usuarioBD.setPassword(usuario.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(
                serviceUsuario.updateUser(usuarioBD)
        );
    }
}
