package com.alura.excepcions;

import com.alura.excepcions.ExcepcionesPersonalizadas.UserNotFoundException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
public class HandlerExceptions {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity urlIncorrecta(){
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).body("Url invalida");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity argumentosInvalidos(MethodArgumentNotValidException e){
        var errores=e.getFieldErrors().stream().map(DatosError::new).toList();

        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity handleUsuarioNotFoundException(UserNotFoundException ex) {

        return ResponseEntity.badRequest().body(ex.getMessage());
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity handleDataIntegrityViolationException(DataIntegrityViolationException ex){
        String mensajeExcepcion = ex.getLocalizedMessage();
        String mensajeError="Campos duplicados";
        if (mensajeExcepcion.contains("topicos.titulo")) {
            mensajeError = "Error: El título ya existe, por favor ingrese un título único.";
        } else if (mensajeExcepcion.contains("topicos.otro_campo")) {
            mensajeError = "Error: El otro campo ya existe, por favor ingrese un valor único.";
        }
        return ResponseEntity.badRequest().body(mensajeError);
    }

    public record DatosError(String campo,String error){
        public DatosError(FieldError er){
            this(er.getField(),er.getDefaultMessage());
        }
    }
}
