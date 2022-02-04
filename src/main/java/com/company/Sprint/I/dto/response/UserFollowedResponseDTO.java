package com.company.Sprint.I.dto.response;

import com.company.Sprint.I.dto.SellerDTO;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)

public class UserFollowedResponseDTO implements Serializable {
    private Integer userId;
    private String userName;
    private List<SellerDTO> followed;
}
