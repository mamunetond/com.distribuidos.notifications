package com.distribuidos.notifications.models;

import java.beans.ConstructorProperties;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseBody<T> {
    
    private final T result;
    private final int status;
    private final String error;
    
    private static <T> int $default$status() {
        return 200;
    }
    
    @ConstructorProperties({"result", "status", "error"})
    ResponseBody(T result, int status, String error) {
        this.result = result;
        this.status = status;
        this.error = error;
    }
    
    public static <T> ResponseBody.ResponseBodyBuilder<T> builder() {
        return new ResponseBody.ResponseBodyBuilder();
    }
    
    public String getError() {
        return this.error;
    }
    
    public T getResult() {
        return this.result;
    }
    
    public Integer getStatus() {
        return this.status;
    }
    
    public static class ResponseBodyBuilder<T> {
        private T result;
        private boolean status$set;
        private int status$value;
        private String error;
        
        ResponseBodyBuilder() {
        }
        
        public ResponseBody.ResponseBodyBuilder<T> error(String error) {
            this.error = error;
            return this;
        }
        
        public ResponseBody.ResponseBodyBuilder<T> result(T result) {
            this.result = result;
            return this;
        }
        
        public ResponseBody.ResponseBodyBuilder<T> status(int status) {
            this.status$value = status;
            this.status$set = true;
            return this;
        }
        
        public ResponseBody<T> build() {
            
            int status$value = this.status$value;
            if (!this.status$set) {
                status$value = ResponseBody.$default$status();
            }
            
            return new ResponseBody(this.result, status$value, this.error);
        }
    }
}
