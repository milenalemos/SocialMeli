package com.company.Sprint.I.dto.request;

import com.company.Sprint.I.dto.ProductDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)

public class PostPromoRequestDTO implements Serializable {

    @JsonProperty("user_id")
    private Integer postId;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;
    private ProductDTO detail;
    private Integer category;
    private Double price;
    private boolean has_promo;
    private Double discount;
}
