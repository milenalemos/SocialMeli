package com.company.Sprint.I.controller;
import com.company.Sprint.I.dto.request.PostPromoRequestDTO;
import com.company.Sprint.I.dto.request.PostRequestDTO;
import com.company.Sprint.I.dto.response.*;
import com.company.Sprint.I.service.seller.ISellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class SellerController {

    @Autowired
    ISellerService sellerService;

    @GetMapping (value = "/users/{userId}/followers/count")
    public ResponseEntity<SellerFollowersResponseDTO> viewCountFollowers(@PathVariable Integer userId){
        return new ResponseEntity<>(sellerService.viewCountFollowers(userId), HttpStatus.OK);
    }

    @GetMapping(value = "/users/{userId}/followers/list")
    public ResponseEntity<SellerResponseDTO> followersList(@PathVariable Integer userId, @RequestParam (defaultValue = "name_asc") String order){
        return new ResponseEntity<>(sellerService.followersList(userId, order), HttpStatus.OK);
    }

    @PostMapping(value = "/products/post")
    public ResponseEntity<MessageResponseDTO> createPost(@RequestBody PostRequestDTO postRequestDTO){
        return new ResponseEntity<>(sellerService.createPost(postRequestDTO), HttpStatus.OK);
    }

    @PostMapping(value = "/products/promo-post")
    public ResponseEntity<MessageResponseDTO> createPostPromo(@RequestBody PostPromoRequestDTO postPromoRequestDTO){
        return new ResponseEntity<>(sellerService.createPostPromo(postPromoRequestDTO), HttpStatus.OK);
    }

    @GetMapping("/products/promo-post/count")
    public ResponseEntity<SellerPostPromoResponseDTO> viewCountPromo(@RequestParam Integer user_id){
        return new ResponseEntity<>(sellerService.viewCountPlomo(user_id), HttpStatus.OK);
    }

    @GetMapping("/products/promo-post/list")
    public ResponseEntity<SellerListPromoResponseDTO> viewPromoList(@RequestParam Integer user_id){
        return new ResponseEntity<>(sellerService.viewPromoList(user_id), HttpStatus.OK);
    }

}
