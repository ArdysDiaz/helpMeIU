package co.helmeiud.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CasoDto {

    private Long id;

    private LocalDateTime fechaHora;

    private float latitud;

    private float longitud;

    private float altitud;

    private Boolean visible;

    private String descripcion;

    private String urlMap;

    private String rmiUrl;

    private Long usuarioId;

    private String image;

    private String nombre;

}
