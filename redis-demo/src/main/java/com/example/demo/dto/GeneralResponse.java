package com.example.demo.dto;

import org.springframework.http.ResponseEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GeneralResponse {
    private String message;
    private Object data;

    public ResponseEntity<?> toResponseEntity() {
        return ResponseEntity.ok(this);
    }
}
