package io.github.leandro12rk.inventory.exception;

import java.time.LocalDateTime;

public record ErrorDetails(
        LocalDateTime timestamp,
        int statusCode,
        String message,
        String errorCode
) {}