package com.company.Sprint.I.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
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

public class SellerDataResponseDTO implements Serializable {
    private Integer userId;
    private String userName;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate savedDate;
}
