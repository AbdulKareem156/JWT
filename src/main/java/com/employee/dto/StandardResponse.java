package com.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandardResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private LocalDateTime timestamp;
    
    public static <T> StandardResponse<T> success(T data) {
        return new StandardResponse<>(true, "Operation successful", data, LocalDateTime.now());
    }
    
    public static <T> StandardResponse<T> success(String message, T data) {
        return new StandardResponse<>(true, message, data, LocalDateTime.now());
    }
    
    public static <T> StandardResponse<T> error(String message) {
        return new StandardResponse<>(false, message, null, LocalDateTime.now());
    }
}
