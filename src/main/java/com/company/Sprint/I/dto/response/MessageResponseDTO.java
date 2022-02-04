package com.company.Sprint.I.dto.response;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class MessageResponseDTO implements Serializable {

    private String message;

}
