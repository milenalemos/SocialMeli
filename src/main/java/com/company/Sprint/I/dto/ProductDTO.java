package com.company.Sprint.I.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)

public class ProductDTO implements Serializable {

    private Integer productId;
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;
}
