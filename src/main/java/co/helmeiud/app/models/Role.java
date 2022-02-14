package co.helmeiud.app.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 45)
    private String nombre;

    @Column(nullable = true, length = 45)
    private String descripcion;

    @ManyToMany(mappedBy = "roles")
    private List<Usuario> usuarios;
}
