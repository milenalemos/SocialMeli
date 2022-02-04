package com.company.Sprint.I.repository.user;

import com.company.Sprint.I.entity.SellerEntity;
import com.company.Sprint.I.entity.UserEntity;

import java.util.Optional;

public interface IUserRepository {

    Optional<UserEntity> findByID(Integer userId);

    void saveSeller(Integer userId, SellerEntity sellerEntity);

    void deleteSeller(Integer userId, int indexSeller);
}
