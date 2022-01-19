package com.iudigital.helpmeiu.models;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "casos")
@AllArgsConstructor
@NoArgsConstructor
public class Caso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime fechaHora;
    private float latitud;
    private float longitud;
    private float altitud;
    private boolean visible;

    private String descripcion;
    @Column(columnDefinition = "TEXT")
    private String urlMap;
    @Column(columnDefinition = "TEXT")
    private String rmiUrl;

    @ManyToOne
    @JoinColumn(name = "delito_id")
    private Delito delito;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @PrePersist
    public void prePersist() {
        if (fechaHora == null) {
            fechaHora = LocalDateTime.now();
        }
    }
}
