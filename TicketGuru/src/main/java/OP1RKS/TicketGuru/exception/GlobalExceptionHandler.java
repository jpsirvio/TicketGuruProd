package OP1RKS.TicketGuru.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import OP1RKS.TicketGuru.domain.ErrorResponse;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	// Authentication Exception
	@ExceptionHandler({ AuthenticationException.class })
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException ex) {
		LocalDateTime timestamp = LocalDateTime.now();
		ErrorResponse error = new ErrorResponse(timestamp, HttpStatus.UNAUTHORIZED.value(), "Authentication failed");
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }
	
	// Access denied Exception
	@ExceptionHandler({ AccessDeniedException.class })
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex) {
		LocalDateTime timestamp = LocalDateTime.now();
		ErrorResponse error = new ErrorResponse(timestamp, HttpStatus.FORBIDDEN.value(), "Access denied");
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }
	
	// Validation Exception
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }
	
	// Bad Request Exception
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException ex) {
        LocalDateTime timestamp = LocalDateTime.now();
        ErrorResponse error = new ErrorResponse(timestamp, HttpStatus.BAD_REQUEST.value(), ex.getReason());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    
    // Not Found Exception
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        LocalDateTime timestamp = LocalDateTime.now();
        ErrorResponse error = new ErrorResponse(timestamp, HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    
    // Conflict Exception
    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<ErrorResponse> handleEntityExistsException(EntityExistsException ex) {
        LocalDateTime timestamp = LocalDateTime.now();
        ErrorResponse error = new ErrorResponse(timestamp, HttpStatus.CONFLICT.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    } 
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        LocalDateTime timestamp = LocalDateTime.now();
        ErrorResponse error = new ErrorResponse(timestamp, HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
