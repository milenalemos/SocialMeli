package com.company.Sprint.I.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString

public class BadRequestException extends RuntimeException{

    private final HttpStatus status;
    private final String exception;

    public BadRequestException(Integer userId) {
        super(String.format("Unable to find id " + userId));
        this.status = HttpStatus.BAD_REQUEST;
        this.exception = "Bad RequestException";
    }

    public BadRequestException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
        this.exception = "Bad Request Exception";
    }

}
