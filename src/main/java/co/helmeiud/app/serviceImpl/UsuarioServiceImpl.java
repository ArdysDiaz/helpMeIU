package co.helmeiud.app.serviceImpl;

import co.helmeiud.app.dto.UsuarioDto;
import co.helmeiud.app.exception.BadRequestException;
import co.helmeiud.app.exception.ErrorDto;
import co.helmeiud.app.exception.NotFoundException;
import co.helmeiud.app.exception.RestException;
import co.helmeiud.app.models.Role;
import co.helmeiud.app.models.Usuario;
import co.helmeiud.app.repository.RepoUsuario;
import co.helmeiud.app.service.ServiceUsuario;
import co.helmeiud.app.util.ConstUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements ServiceUsuario {

	private RepoUsuario repoUsuario;
	
	@Transactional(readOnly = true)
	@Override
	public List<UsuarioDto> listUsers() throws RestException {
		List<Usuario> usuariosDB = repoUsuario.findAll();
		List<UsuarioDto> usuarios = new ArrayList<>();
		usuariosDB.stream()
				.forEach(u -> {
					UsuarioDto usuarioDto = new UsuarioDto();
					usuarioDto.setId(u.getId());
					usuarioDto.setNombre(u.getNombre());
					usuarioDto.setApellido(u.getApellido());
					usuarioDto.setUsername(u.getUsername());
					usuarioDto.setFechaNacimiento(u.getFechaNacimiento());
					usuarioDto.setEnabled(u.getEnabled());
					usuarioDto.setImage(u.getImage());
					List<String> rols = (u.getRoles()).stream()
													.map(role -> role.getNombre())
													.collect(Collectors.toList());
					usuarioDto.setRoles(rols);
					usuarios.add(usuarioDto);
				});
		return usuarios;
	}
	
	
	@Transactional(readOnly = true)
	@Override
	public Usuario listUser(Long id) throws RestException {
		Optional<Usuario> usuarioDB =  repoUsuario.findById(id);
		if(!usuarioDB.isPresent()) {
			throw new NotFoundException(ErrorDto.getErrorDto(
					HttpStatus.NOT_FOUND.getReasonPhrase(), 
					ConstUtil.MESSAGE_NOT_FOUND,
					HttpStatus.NOT_FOUND.value())
				);
		}
		return repoUsuario.findById(id).get();
	}

	@Transactional
	@Override
	public Usuario saveUser(Usuario usuario) throws RestException {
		if(Objects.isNull(usuario)) {
			throw new BadRequestException(ErrorDto.getErrorDto(
					HttpStatus.BAD_REQUEST.getReasonPhrase(), 
					"Mala petición", //TODO: CREAR CONSTANTE EN CONSUTIL
					HttpStatus.BAD_REQUEST.value())
				);
		}
		Usuario usuarioDb = repoUsuario.findByUsername(usuario.getUsername());
		if(Objects.nonNull(usuarioDb)) {
			throw new BadRequestException(ErrorDto.getErrorDto(
					HttpStatus.BAD_REQUEST.getReasonPhrase(), 
					"Usuario ya existe",
					HttpStatus.BAD_REQUEST.value())
					);
		}
		List<Role> roles = new ArrayList<>();
		Role role = new Role();
		role.setId(2L);
		roles.add(role);
		usuario.setRoles(roles);
		return repoUsuario.save(usuario);
	}

	@Transactional(readOnly = true)
	@Override
	public Usuario listByUsername(String username) {
		return repoUsuario.findByUsername(username);
	}

	@Transactional
	@Override
	public Usuario updateUser(Usuario usuario) throws RestException {
		if(Objects.isNull(usuario)) {
			throw new BadRequestException(ErrorDto.getErrorDto(
					HttpStatus.BAD_REQUEST.getReasonPhrase(), 
					"Mala petición", //TODO: CREAR CONSTANTE EN CONSUTIL
					HttpStatus.BAD_REQUEST.value())
				);
		}
		return repoUsuario.save(usuario);
	}

}
