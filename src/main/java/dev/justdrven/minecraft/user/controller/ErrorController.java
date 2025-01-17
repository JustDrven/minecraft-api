package dev.justdrven.minecraft.user.controller;

import dev.justdrven.minecraft.user.exception.AccessDeniedException;
import dev.justdrven.minecraft.user.exception.BadRequestBodyException;
import dev.justdrven.minecraft.user.exception.UserAlreadyExistException;
import dev.justdrven.minecraft.user.exception.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> accessDenied(AccessDeniedException e) {
        String builder = "{" + "\"success\":" + "false," +
                "\"message\":\"Forbidden!\"" +
                "}";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        return new ResponseEntity<>(builder, headers, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> userNotFound(UserNotFoundException e) {
        String builder = "{" + "\"success\":" + "false," +
                "\"message\":\"This user doesn't exist!\"" +
                "}";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        return new ResponseEntity<>(builder, headers, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<String> userAlreadyExist(UserAlreadyExistException e) {
        String builder = "{" + "\"success\":" + "false," +
                "\"message\":\"This user already exist!\"" +
                "}";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        return new ResponseEntity<>(builder, headers, HttpStatus.FOUND);
    }


    @ExceptionHandler(BadRequestBodyException.class)
    public ResponseEntity<String> badRequest(BadRequestBodyException e) {
        String builder = "{" + "\"success\":" + "false," +
                "\"message\":\"Request's body isn't valid!\"" +
                "}";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        return new ResponseEntity<>(builder, headers, HttpStatus.BAD_REQUEST);
    }

}
