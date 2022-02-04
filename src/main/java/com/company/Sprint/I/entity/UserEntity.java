package com.company.Sprint.I.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString

public class UserEntity extends People {

    private List<SellerEntity> followed;

    public UserEntity(Integer userId, String userName) {
        super(userId, userName);
        this.followed = new ArrayList<>();
    }

}
