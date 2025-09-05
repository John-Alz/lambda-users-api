package com.users.application.dto.response;

import java.time.LocalDateTime;

public record SuccessMessageResponseDto(String message, LocalDateTime time) {
}
