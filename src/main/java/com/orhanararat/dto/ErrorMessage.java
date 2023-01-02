package com.orhanararat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ErrorMessage implements Serializable {
    private int statusCode;

    private String message;

    private LocalDateTime timestamp;

    private String description;
}
