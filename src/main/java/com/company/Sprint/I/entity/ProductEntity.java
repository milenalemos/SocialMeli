package com.company.Sprint.I.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ProductEntity {

    private Integer productId;
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;

}
