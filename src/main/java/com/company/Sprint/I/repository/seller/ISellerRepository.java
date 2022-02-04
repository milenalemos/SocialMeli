package com.company.Sprint.I.repository.seller;

import com.company.Sprint.I.entity.PostEntity;
import com.company.Sprint.I.entity.SellerEntity;
import com.company.Sprint.I.entity.UserEntity;

import java.util.Optional;

public interface ISellerRepository {

    Optional<SellerEntity> findByID(Integer userId);

    void saveUser(Integer userIdToFollow, UserEntity userEntity);

    void savePost(Integer userId, PostEntity postEntity);

    void deleteUser(Integer userIdToUnfollow, int indexUser);
}
