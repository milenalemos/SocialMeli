package com.company.Sprint.I.dto.response;

import com.company.Sprint.I.entity.ProductEntity;
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

public class PostPromoDTO implements Serializable {

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
