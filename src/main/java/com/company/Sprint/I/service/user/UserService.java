package com.company.Sprint.I.service.user;

import com.company.Sprint.I.dto.PostDTO;
import com.company.Sprint.I.dto.response.MessageResponseDTO;
import com.company.Sprint.I.dto.response.UserFollowedResponseDTO;
import com.company.Sprint.I.dto.response.UserPostResponseDTO;
import com.company.Sprint.I.entity.SellerEntity;
import com.company.Sprint.I.entity.UserEntity;
import com.company.Sprint.I.exception.BadRequestException;
import com.company.Sprint.I.repository.user.IUserRepository;
import com.company.Sprint.I.service.seller.ISellerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service

public class UserService implements IUserService {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    ISellerService sellerService;

    ModelMapper mapper = new ModelMapper();

    @Override
    public MessageResponseDTO follow(Integer userId, Integer userIdToFollow) {

        // Verifico si encuentro en el repositorio un UserEntity
        UserEntity userEntity = userRepository.findByID(userId).orElseThrow(() -> new BadRequestException(userId));

        // Instancio el mensaje que le voy a pasar
        MessageResponseDTO messageResponseDTO = new MessageResponseDTO();

        // Verifico si el Seller existe
        SellerEntity sellerEntity = sellerService.findByIdService(userIdToFollow);

        // Verifico si mi User tiene ya al Seller en la lista de Fav
        Optional<SellerEntity> sellerEntityOptional = userEntity.getFollowed().stream()
                .filter(sellerEntityUno -> sellerEntityUno.getUserId().equals(userIdToFollow))
                .findFirst();

        // Chequeo si el Seller ya esta en la lista de sellerFav del User y retorno un mensaje
        if (sellerEntityOptional.isPresent()){
            throw new BadRequestException("The seller is already in your favorites list.");
        } else {
            messageResponseDTO.setMessage("The seller was added to your favorites list.");

            // Le digo al repositorio que me lo guarde
            userRepository.saveSeller(userId, sellerEntity);

            // le digo al Seller que guarda al usuario
            sellerService.saveUser(userIdToFollow, userEntity);
        }
        return messageResponseDTO;
    }

    @Override
    public UserFollowedResponseDTO followed(Integer userId) {

        UserEntity userEntity = userRepository.findByID(userId).orElseThrow(() -> new BadRequestException(userId));

        UserFollowedResponseDTO userFollowedResponseDTO = mapper.map(userEntity, UserFollowedResponseDTO.class);

        if (userFollowedResponseDTO.getFollowed().size() == 0){
            throw new BadRequestException("The user has no favorite sellers.");
        }

        return userFollowedResponseDTO;
    }

    @Override
    public UserPostResponseDTO viewPosts(Integer userId, String order) {

        UserEntity userEntity = userRepository.findByID(userId).orElseThrow(() -> new BadRequestException(userId));

        // Consigo los ID de los Sellers que sigue hace menos de 14 días, aplano la lista de PostEntity
        // y la mapeo a PostDTO.

        List<PostDTO> postDTOList = userEntity.getFollowed().stream()
                .map(sellerEntity ->
                        sellerEntity.getPots().stream()
                            .filter(postEntity ->
                                        postEntity.getDate().isAfter(LocalDate.now().minusDays(14)))
                            .collect(Collectors.toList()))
                .flatMap(Collection::stream)
                .map(postEntity -> mapper.map(postEntity, PostDTO.class))
                .collect(Collectors.toList());

        UserPostResponseDTO userPostResponseDTO = mapper.map(userEntity, UserPostResponseDTO.class);

        // Ordeno la lista por fecha, por defecto en ASC, en caso de no ingresar un orden valido se lanza una excepción.
        if (order.toLowerCase(Locale.ROOT).equals("name_asc")){
            postDTOList.sort(Comparator.comparing(PostDTO::getDate));
        } else if (order.toLowerCase(Locale.ROOT).equals("name_desc")){
            postDTOList.sort(Comparator.comparing(PostDTO::getDate).reversed());
        } else {
            throw new BadRequestException("Indicate a valid order.");
        }

        userPostResponseDTO.setPosts(postDTOList);

        // Verifico si la lista tiene algun valor o lanzo una excepción.
        if (userPostResponseDTO.getPosts().size() == 0){
            throw new BadRequestException("The seller has no post in the last 14 days.");
        }
        
        return userPostResponseDTO;
    }

    @Override
    public MessageResponseDTO unfollow(Integer userId, Integer userIdToUnfollow) {

        int indexSeller = -1;
        int indexUser = -1;

        // Chequeo si el User existe
        UserEntity userEntity = userRepository.findByID(userId).orElseThrow(() -> new BadRequestException(userId));

        // Chequeo si el Seller existe
        SellerEntity sellerEntity = sellerService.findByIdService(userIdToUnfollow);

        // Verifico si ese Seller se encuentra entre los seguidos del User

        Optional<SellerEntity> sellerEntityOptional = userEntity.getFollowed().stream()
                .filter(sellerEntity1 -> sellerEntity1.getUserId().equals(userIdToUnfollow))
                .findFirst();

        // Verifico si el Optional tiene algun valor
        if (sellerEntityOptional.isEmpty()){
            throw new BadRequestException("The seller is not among the User's following list.");
        } else {

            for (SellerEntity sellerEntity1: userEntity.getFollowed()) {
                if (sellerEntity1.getUserId().equals(userIdToUnfollow)){
                    indexSeller = userEntity.getFollowed().indexOf(sellerEntity1);
                }
            }

            for (UserEntity userEntity1 : sellerEntity.getFollowers()){
                if (userEntity1.getUserId().equals(userId)){
                    indexUser = sellerEntity.getFollowers().indexOf(userEntity1);
                }
            }

            userRepository.deleteSeller(userId, indexSeller);
            sellerService.deleteUser(userIdToUnfollow, indexUser);
        }

        return new MessageResponseDTO("The seller was successfully removed.");
    }
}
