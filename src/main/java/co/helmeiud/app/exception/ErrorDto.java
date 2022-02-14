package co.helmeiud.app.exception;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ErrorDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String error;

    private String message;

    private int status;

    private LocalDateTime date;

    public static ErrorDto getErrorDto(String error, String message, int status) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setError(error);
        errorDto.setMessage(message);
        errorDto.setStatus(status);
        errorDto.setDate(LocalDateTime.now());
        return errorDto;
    }

}
