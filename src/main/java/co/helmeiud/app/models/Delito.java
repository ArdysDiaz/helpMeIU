package co.helmeiud.app.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class Delito implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @NotNull
    @Column(nullable = false, length = 45)
    private String nombre;

    @Column(nullable = true, length = 120)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "usuarios_id")
    private Usuario usuario;
}
