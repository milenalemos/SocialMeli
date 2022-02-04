package com.company.Sprint.I.service.user;

import com.company.Sprint.I.dto.response.MessageResponseDTO;
import com.company.Sprint.I.dto.response.UserFollowedResponseDTO;
import com.company.Sprint.I.dto.response.UserPostResponseDTO;


public interface IUserService {

    MessageResponseDTO follow(Integer userId, Integer userIdToFollow);

    UserFollowedResponseDTO followed(Integer userId);

    UserPostResponseDTO viewPosts(Integer userId, String order);

    MessageResponseDTO unfollow(Integer userId, Integer userIdToUnfollow);
}
