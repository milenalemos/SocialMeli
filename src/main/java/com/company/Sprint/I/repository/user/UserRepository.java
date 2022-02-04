package com.company.Sprint.I.repository.user;

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

public class UserRepository implements IUserRepository {

    List<UserEntity> users;

    public UserRepository() {
        users = loadDataBase();
    }

    private List<UserEntity> loadDataBase(){
        List<UserEntity> users = null;

        File file;
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .registerModule(new JavaTimeModule());

        TypeReference<List<UserEntity>> typeRef = new TypeReference<>() {};

        try {
            file = ResourceUtils.getFile("classpath:json/users.json");
            users = objectMapper.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public Optional<UserEntity> findByID(Integer userId){

        return users.stream()
                .filter(userEntity -> userEntity.getUserId().equals(userId))
                .findFirst();
    }

    @Override
    public void saveSeller(Integer userId, SellerEntity sellerEntity) {

        UserEntity userEntity = findByID(userId).get();

        users.get(users.indexOf(userEntity))
                .getFollowed().add(sellerEntity);
    }

    @Override
    public void deleteSeller(Integer userId, int indexSeller) {

        UserEntity userEntity = findByID(userId).get();

        users.get(users.indexOf(userEntity)).getFollowed().remove(indexSeller);
    }
}
