package co.helmeiud.app.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Email es obligatorio")
    @Email(message = "Debe ingresar un email válido")
    @Column(unique = true, length = 120)
    private String username;

    @Column(length = 120)
    private String password;

    @NotEmpty(message = "nombre es obligatorio")
    @Column(nullable = false, length = 120)
    private String nombre;

    @NotEmpty(message = "apellido es obligatorio")
    @Size(min = 1, max = 12, message = "Fuera de rango")
    @Column(nullable = true, length = 120)
    private String apellido;

    private LocalDate fechaNacimiento;

    private Boolean enabled;

    private Boolean redSocial;

    private String image;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "roles_usuarios",
            joinColumns = {@JoinColumn(name = "usuarios_id")},
            inverseJoinColumns = {@JoinColumn(name = "roles_id")})
    private List<Role> roles;
}
