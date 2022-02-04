package com.company.Sprint.I.controller;

import com.company.Sprint.I.dto.response.MessageResponseDTO;
import com.company.Sprint.I.dto.response.UserFollowedResponseDTO;
import com.company.Sprint.I.dto.response.UserPostResponseDTO;
import com.company.Sprint.I.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping(path = "/users/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<MessageResponseDTO> follow(@PathVariable Integer userId, @PathVariable Integer userIdToFollow){
        return new ResponseEntity<>(userService.follow(userId, userIdToFollow), HttpStatus.OK);
    }

    @GetMapping(value = "/users/{userId}/followed/list")
    public ResponseEntity<UserFollowedResponseDTO> followed(@PathVariable Integer userId){
        return new ResponseEntity<>(userService.followed(userId), HttpStatus.OK);
    }

    @GetMapping(value = "/products/followed/{userId}/list")
    public ResponseEntity<UserPostResponseDTO> viewPosts(@PathVariable Integer userId, @RequestParam (defaultValue = "name_asc") String order){
        return new ResponseEntity<>(userService.viewPosts(userId, order),HttpStatus.OK);
    }

    @PostMapping(value = "/users/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<MessageResponseDTO> unfollow(@PathVariable Integer userId, @PathVariable Integer userIdToUnfollow){
        return new ResponseEntity<>(userService.unfollow(userId, userIdToUnfollow), HttpStatus.OK);
    }
}
