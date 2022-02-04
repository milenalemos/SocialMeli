package com.company.Sprint.I.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class PostEntity {

    @JsonProperty("user_id")
    private Integer postId;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;
    private ProductEntity detail;
    private Integer category;
    private Double price;
    private boolean has_promo;
    private Double discount;

}
