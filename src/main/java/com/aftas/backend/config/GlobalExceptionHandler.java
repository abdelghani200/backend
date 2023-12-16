package com.aftas.backend.config;

import com.aftas.backend.exception.CompetitionValidationException;
import com.aftas.backend.exception.RegistrationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {

    public ResponseEntity<String> handleCompetitionValidationException(CompetitionValidationException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> handleRegistrationException(RegistrationException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> handleNotFoundException(NoClassDefFoundError e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

}
