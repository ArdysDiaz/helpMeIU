package co.helmeiud.app.service;


import co.helmeiud.app.dto.UsuarioDto;
import co.helmeiud.app.exception.RestException;
import co.helmeiud.app.models.Usuario;

import java.util.List;

public interface ServiceUsuario {

	public List<UsuarioDto> listUsers() throws RestException;
	
	public Usuario listUser(Long id) throws RestException;
	
	public Usuario saveUser(Usuario usuario) throws RestException;
	
	public Usuario listByUsername(String username);
	
	public Usuario updateUser(Usuario usuario) throws RestException;
}
