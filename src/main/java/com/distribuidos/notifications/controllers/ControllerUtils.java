package com.distribuidos.notifications.controllers;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.ResponseEntity;

import com.distribuidos.notifications.models.ResponseBody;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ControllerUtils {
    
    public static <T> ResponseEntity<ResponseBody<T>> ok(T result) {
        ResponseBody<T> orb = ResponseBody
                .<T>builder()
                .status(OK.value())
                .result(result)
                .build();
        
        return ResponseEntity.ok(orb);
    }
    
    public static <T> ResponseEntity<ResponseBody<T>> created(T result) {
        ResponseBody<T> orb = ResponseBody
                .<T>builder()
                .status(CREATED.value())
                .result(result)
                .build();
        
        return ResponseEntity.ok(orb);
    }
}
