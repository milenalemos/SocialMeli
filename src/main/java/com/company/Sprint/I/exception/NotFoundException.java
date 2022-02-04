package com.company.Sprint.I.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString

public class NotFoundException extends RuntimeException{

    private final HttpStatus status;
    private final String exception;

    public NotFoundException(Integer userId) {
        super(String.format("Unable to find id " + userId));
        this.status = HttpStatus.NOT_FOUND;
        this.exception = "Not Found Exception";
    }

    public NotFoundException(String message) {
        super(message);
        this.status = HttpStatus.NOT_FOUND;
        this.exception = "Not Found Exception";
    }
}
