package co.helmeiud.app.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Caso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;

    private float latitud;

    private float longitud;

    private Boolean visible;

    private String descripcion;

    @Column(name = "url_map")
    private String urlMap;

    @Column(name = "rmi_url")
    private String rmiUrl;

    @ManyToOne
    @JoinColumn(name = "usuarios_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "delitos_id")
    private Delito delito;

    @PrePersist
    public void prePersist() {
        if(fechaHora == null) {
            fechaHora = LocalDateTime.now();
        }
    }
}
