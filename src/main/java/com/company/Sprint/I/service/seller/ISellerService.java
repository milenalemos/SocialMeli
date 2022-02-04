package com.company.Sprint.I.service.seller;

import com.company.Sprint.I.dto.request.PostPromoRequestDTO;
import com.company.Sprint.I.dto.request.PostRequestDTO;
import com.company.Sprint.I.dto.response.*;
import com.company.Sprint.I.entity.SellerEntity;
import com.company.Sprint.I.entity.UserEntity;

public interface ISellerService {

    SellerEntity findByIdService(Integer userIdToFollow);

    SellerFollowersResponseDTO viewCountFollowers(Integer userId);

    SellerResponseDTO followersList(Integer userId, String order);

    MessageResponseDTO createPost(PostRequestDTO productRequestDTO);

    void saveUser(Integer userIdToFollow, UserEntity userEntity);

    void deleteUser(Integer userIdToUnfollow, int userEntity);

    MessageResponseDTO createPostPromo(PostPromoRequestDTO postPromoRequestDTO);

    SellerPostPromoResponseDTO viewCountPlomo(Integer user_id);

    SellerListPromoResponseDTO viewPromoList(Integer user_id);
}
