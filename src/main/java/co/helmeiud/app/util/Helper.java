package co.helmeiud.app.util;



import co.helmeiud.app.dto.UsuarioDto;
import co.helmeiud.app.models.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public interface Helper {

	public static UsuarioDto getMapValuesClient(Usuario usuario) {
		UsuarioDto uDto = new UsuarioDto();
		uDto.setId(usuario.getId());
		uDto.setNombre(usuario.getNombre());
		uDto.setApellido(usuario.getApellido());
		uDto.setFechaNacimiento(usuario.getFechaNacimiento());
		uDto.setImage(usuario.getImage());
		uDto.setEnabled(usuario.getEnabled());
		
		List<String> rolesDto = usuario.getRoles()
				.stream()
				.map(r -> r.getNombre())
				.collect(Collectors.toList());
		
		uDto.setRoles(rolesDto);
		uDto.setUsername(usuario.getUsername());
		return uDto;
	}
	
	public static void setMapValuesClient(List<Usuario> usuarios, 
			List<UsuarioDto> usuariosDto) {
		usuarios.stream()
				.map(usuario -> {
					UsuarioDto uDto = getMapValuesClient(usuario);
					return uDto;
				}).forEach(cDto -> {
					usuariosDto.add(cDto);
				});
	}
}
