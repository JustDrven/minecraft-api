package dev.justdrven.minecraft.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FOUND, reason = "User not found!")
public class UserNotFoundException extends RuntimeException {
}