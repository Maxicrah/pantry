package com.maxi.pantrypos.exception;

import com.maxi.pantrypos.dto.ErrorDTO;
import com.maxi.pantrypos.exception.offer.InvalidOfferException;
import com.maxi.pantrypos.exception.offer.OfferDiscountException;
import com.maxi.pantrypos.exception.offer.OfferNotFoundException;
import com.maxi.pantrypos.exception.product.ProductCodeException;
import com.maxi.pantrypos.exception.product.ProductEntryDateException;
import com.maxi.pantrypos.exception.product.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "timestamp", LocalDateTime.now(),
                "status", HttpStatus.BAD_REQUEST.value(),
                "error", "bad request",
                "message", ex.getMessage()
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "timestamp", LocalDateTime.now(),
                "status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "error", "internal server error",
                "message", "an unexpected error occurred",
                "details", ex.getMessage()
        ));
    }

    @ExceptionHandler(ProductEntryDateException.class)
    public ResponseEntity<ErrorDTO> verifyProductExceptionHandler(ProductEntryDateException ex) {
        ErrorDTO error = ErrorDTO.builder().
                message(ex.getMessage()).
                code("404").
                detail(ex.getDetail()).
                timestamp(LocalDate.now()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ProductCodeException.class})
    public ResponseEntity<ErrorDTO> verifyProductCodeExceptionHandler(ProductCodeException ex) {
        ErrorDTO error = ErrorDTO.builder().
                message(ex.getMessage()).
                code("409").
                detail(ex.getDetail()).
                timestamp(LocalDate.now()).build();
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }


    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorDTO> maxSizeExceptionHandler(MaxUploadSizeExceededException ex) {
        ErrorDTO error = ErrorDTO.builder().
                                    message(ex.getMessage()).
                                    code(ex.getStatusCode().toString()).
                                    detail(ex.getDetailMessageCode()).
                                    timestamp(LocalDate.now()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<ErrorDTO> fileNotFoundExceptionHandler(FileNotFoundException ex) {
        ErrorDTO error = ErrorDTO.builder().
                message(ex.getMessage()).
                code("404").
                detail("file not found").
                timestamp(LocalDate.now()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDTO> productNotFoundExceptionHandler(ProductNotFoundException ex) {
        ErrorDTO error = ErrorDTO.builder().
                message(ex.getMessage()).
                code("404").
                detail(ex.getDetail()).
                timestamp(LocalDate.now()).build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(OfferDiscountException.class)
    public ResponseEntity<ErrorDTO> offerDiscountExceptionHandler(OfferDiscountException ex) {
        ErrorDTO error = ErrorDTO.builder().
                message(ex.getMessage()).
                code("404").
                detail(ex.getDetail()).
                timestamp(LocalDate.now()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidOfferException.class)
    public ResponseEntity<ErrorDTO> invalidOfferExceptionHandler(InvalidOfferException ex) {
        ErrorDTO error = ErrorDTO.builder().
                message(ex.getMessage()).
                code("404").
                detail(ex.getDetail()).
                timestamp(LocalDate.now()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OfferNotFoundException.class)
    public ResponseEntity<ErrorDTO> offerNotFoundExceptionHandler(OfferNotFoundException ex) {
        ErrorDTO error = ErrorDTO.builder().
                message(ex.getMessage()).
                code("404").
                detail("offer no exists in database").
                timestamp(LocalDate.now()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
