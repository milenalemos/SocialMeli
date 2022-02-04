package com.company.Sprint.I.repository.seller;

import com.company.Sprint.I.entity.PostEntity;
import com.company.Sprint.I.entity.SellerEntity;
import com.company.Sprint.I.entity.UserEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Repository

public class SellerRepository implements ISellerRepository {

    List<SellerEntity> sellers;

    public SellerRepository() {
        sellers = loadDataBase();
    }

    private List<SellerEntity> loadDataBase(){
        List<SellerEntity> sellers = null;

        File file;
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .registerModule(new JavaTimeModule());

        TypeReference<List<SellerEntity>> typeRef = new TypeReference<>() {};

        try {
            file = ResourceUtils.getFile("classpath:json/sellers.json");
            sellers = objectMapper.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sellers;
    }

    @Override
    public Optional<SellerEntity> findByID(Integer userId){

        return sellers.stream()
                .filter(sellerEntity -> sellerEntity.getUserId().equals(userId))
                .findFirst();
    }

    @Override
    public void saveUser(Integer userIdToFollow, UserEntity userEntity) {

        SellerEntity sellerEntity = findByID(userIdToFollow).get();

        sellers.get(sellers.indexOf(sellerEntity))
                .getFollowers().add(userEntity);
    }

    @Override
    public void savePost(Integer userId, PostEntity postEntity) {

        SellerEntity sellerEntity = findByID(userId).get();

        sellers.get(sellers.indexOf(sellerEntity))
                .getPots().add(postEntity);
    }

    @Override
    public void deleteUser(Integer userIdToUnfollow, int indexUser) {

        SellerEntity sellerEntity = findByID(userIdToUnfollow).get();

        sellers.get(sellers.indexOf(sellerEntity)).getFollowers().remove(indexUser);
    }

}
