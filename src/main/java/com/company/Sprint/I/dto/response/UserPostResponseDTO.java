package com.company.Sprint.I.dto.response;

import com.company.Sprint.I.dto.PostDTO;
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

public class UserPostResponseDTO implements Serializable {

    private Integer userId;
    private List<PostDTO> posts;
}
