package com.company.Sprint.I.dto.exception;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ErrorDTO implements Serializable {

    private String exception;
    private String status;
    private String errorMessage;
}
