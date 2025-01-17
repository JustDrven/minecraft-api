package dev.justdrven.minecraft.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Forbidden!")
public class AccessDeniedException extends RuntimeException {
}
