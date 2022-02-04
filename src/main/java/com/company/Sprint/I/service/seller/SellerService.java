package com.company.Sprint.I.service.seller;

import com.company.Sprint.I.dto.request.PostPromoRequestDTO;
import com.company.Sprint.I.dto.request.PostRequestDTO;
import com.company.Sprint.I.dto.response.*;
import com.company.Sprint.I.entity.PostEntity;
import com.company.Sprint.I.entity.SellerEntity;
import com.company.Sprint.I.entity.UserEntity;
import com.company.Sprint.I.exception.BadRequestException;
import com.company.Sprint.I.repository.seller.ISellerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service

public class SellerService implements ISellerService {

    @Autowired
    ISellerRepository sellerRepository;

    ModelMapper mapper = new ModelMapper();

    @Override
    public SellerEntity findByIdService(Integer userIdToFollow) {
        return sellerRepository.findByID(userIdToFollow).orElseThrow(() -> new BadRequestException(userIdToFollow));
    }

    @Override
    public SellerFollowersResponseDTO viewCountFollowers(Integer userId) {

        SellerEntity sellerEntity = sellerRepository.findByID(userId).orElseThrow(() -> new BadRequestException(userId));

        SellerFollowersResponseDTO sellerFollowersResponseDTO = mapper.map(sellerEntity, SellerFollowersResponseDTO.class);

        sellerFollowersResponseDTO.setFollowersCount(sellerEntity.getFollowers().size());

        if (sellerFollowersResponseDTO.getFollowersCount() == 0){
            throw new BadRequestException("Seller has no followers.");
        }

        return sellerFollowersResponseDTO;
    }

    @Override
    public SellerResponseDTO followersList(Integer userId, String order) {

        SellerEntity sellerEntity = sellerRepository.findByID(userId).orElseThrow(() -> new BadRequestException(userId));

        SellerResponseDTO sellerResponseDTO = mapper.map(sellerEntity, SellerResponseDTO.class);

        // Verifico el orden solicitado, por defecto en ASC, en caso de no ser un orden valido lanzo una excepción.
        if (order.toLowerCase(Locale.ROOT).equals("name_asc")){
            sellerResponseDTO.getFollowers().sort(Comparator.comparing(UserResponseDTO::getUserName));
        } else if (order.toLowerCase(Locale.ROOT).equals("name_desc")){
            sellerResponseDTO.getFollowers().sort(Comparator.comparing(UserResponseDTO::getUserName).reversed());
        } else {
            throw new BadRequestException("Indicate a valid order.");
        }

        // Verifico si la lista tiene algun valor, en caso de estar vacia lanzo una excepción.
        if (sellerResponseDTO.getFollowers().size() == 0){
            throw new BadRequestException("Seller has no followers.");
        }

        return sellerResponseDTO;
    }

    @Override
    public void saveUser(Integer userIdToFollow, UserEntity userEntity) {
        sellerRepository.saveUser(userIdToFollow, userEntity);
    }

    @Override
    public void deleteUser(Integer userIdToUnfollow, int indexUser) {
        sellerRepository.deleteUser(userIdToUnfollow, indexUser);
    }

    @Override
    public MessageResponseDTO createPost(PostRequestDTO postRequestDTO) {

        Integer userId = postRequestDTO.getPostId();

        // Busco si el vendedor que me pasaron como ID en el post existe.
        SellerEntity sellerEntity = sellerRepository.findByID(userId).orElseThrow(() -> new BadRequestException(userId));

        // Mapeo el PostDTO a una PostEntity
        PostEntity postEntity = mapper.map(postRequestDTO, PostEntity.class);

        // Me guardo el postEntity en la entidad del Seller.
        sellerRepository.savePost(userId, postEntity);

        return new MessageResponseDTO("The post was successfully created in the userID " + userId + ".");
    }

    @Override
    public MessageResponseDTO createPostPromo(PostPromoRequestDTO postPromoRequestDTO) {

        SellerEntity sellerEntity = sellerRepository.findByID(postPromoRequestDTO.getPostId()).orElseThrow(() -> new BadRequestException(postPromoRequestDTO.getPostId()));

        PostEntity postEntity = mapper.map(postPromoRequestDTO, PostEntity.class);

        sellerRepository.savePost(postPromoRequestDTO.getPostId(), postEntity);

        return new MessageResponseDTO("The post was successfully created in the userID " + postPromoRequestDTO.getPostId() + ".");
    }

    @Override
    public SellerPostPromoResponseDTO viewCountPlomo(Integer user_id) {

        SellerEntity sellerEntity = sellerRepository.findByID(user_id).orElseThrow(() -> new BadRequestException(user_id));

        List<PostEntity> postEntities = sellerEntity.getPots().stream()
                .filter(PostEntity::isHas_promo).collect(Collectors.toList());

        if (postEntities.isEmpty()){
            throw new BadRequestException("The seller has no products in promotion.");
        }

        SellerPostPromoResponseDTO sellerPostPromoResponseDTO = mapper.map(sellerEntity, SellerPostPromoResponseDTO.class);
        sellerPostPromoResponseDTO.setPromoProductsCount(postEntities.size());

        return sellerPostPromoResponseDTO;
    }

    @Override
    public SellerListPromoResponseDTO viewPromoList(Integer user_id) {

        SellerEntity sellerEntity = sellerRepository.findByID(user_id).orElseThrow(() -> new BadRequestException(user_id));

        List<PostPromoDTO> postPromoDTOList = sellerEntity.getPots().stream()
                .filter(PostEntity::isHas_promo)
                .map(postEntity -> mapper.map(postEntity, PostPromoDTO.class))
                .collect(Collectors.toList());

        if (postPromoDTOList.isEmpty()){
            throw new BadRequestException("The seller has no products in promotion.");
        }

        SellerListPromoResponseDTO sellerListPromoResponseDTO = mapper.map(sellerEntity, SellerListPromoResponseDTO.class);
        sellerListPromoResponseDTO.setPosts(postPromoDTOList);

        return sellerListPromoResponseDTO;
    }

}
