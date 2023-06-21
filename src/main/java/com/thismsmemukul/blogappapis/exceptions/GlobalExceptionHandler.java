package com.thismsmemukul.blogappapis.exceptions;

import com.thismsmemukul.blogappapis.paylodes.ApiError;
import com.thismsmemukul.blogappapis.paylodes.ApiResponse;
import com.thismsmemukul.blogappapis.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        String message = ex.getMessage();
        ApiResponse response = ApiResponse.builder().message(message).success(false).status(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> resp = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            resp.put(fieldName, message);
        });

        return new ResponseEntity<Map<String, String>>(resp, HttpStatus.BAD_REQUEST);
    }


    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode httpStatus, WebRequest request) {
        Map<String, Object> errorMap = new LinkedHashMap<>();
        errorMap.put("Current Timestamp", new Date());
        errorMap.put("Status", httpStatus.value());

        // Get all errors
        List<String> exceptionalErrors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        errorMap.put("Errors", exceptionalErrors);

        return new ResponseEntity<>(errorMap, httpStatus);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception ex) {
        ApiResponse response = ApiResponse.builder().message(ex.getMessage()).success(false).status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException ex) {

        if (ex.getStatusCode().is4xxClientError()) {

            String error = ex.getMessage();

            String exMessage = error.substring(ex.getMessage().indexOf(':') + 1).trim();

            exMessage = exMessage.substring(1, exMessage.length() - 1);


            ApiError apiError =
                    new ApiError(HttpStatus.BAD_REQUEST, exMessage, exMessage);
            return new ResponseEntity<Object>(
                    apiError, apiError.getStatus());


        } else if (ex.getStatusCode().is5xxServerError()) {

            String s = Constants.SERVER_DOWN_MESSAGE;

            ApiError apiError =
                    new ApiError(HttpStatus.BAD_REQUEST, s, s);
            return new ResponseEntity<Object>(
                    apiError, apiError.getStatus());

        }


        return null;


    }


    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Object> handleIllegalStateException(IllegalStateException ex) {

        String s = Constants.SERVER_DOWN_MESSAGE;

        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, s, s);
        return new ResponseEntity<Object>(
                apiError, apiError.getStatus());

    }
}
