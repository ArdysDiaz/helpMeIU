package co.helmeiud.app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class UsuarioDto {

	
	private Long id;

	private String username;
	
	private String nombre;

	private String apellido;
	
	private LocalDate fechaNacimiento;
	
	private Boolean enabled;
	
	private String image;
	
	private List<String> roles;

}
