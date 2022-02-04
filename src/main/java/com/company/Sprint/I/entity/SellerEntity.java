package com.company.Sprint.I.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class SellerEntity extends People {

    private List<UserEntity> followers;
    private List<PostEntity> pots;

    public SellerEntity(Integer userId, String userName) {
        super(userId, userName);
        this.followers = new ArrayList<>();
        this.pots = new ArrayList<>();
    }

}
