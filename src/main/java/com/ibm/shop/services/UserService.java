package com.ibm.shop.services;

import com.ibm.shop.data.vo.StateVO;
import com.ibm.shop.data.vo.UserVO;
import com.ibm.shop.entities.State;
import com.ibm.shop.entities.User;
import com.ibm.shop.exceptions.ResourceNotFoundException;
import com.ibm.shop.mapper.IbmShopMapper;
import com.ibm.shop.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class UserService {

    private final Logger logger = Logger.getLogger(UserService.class.getName());

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public UserVO findByUsernameOrEmail(String usernameOrEmail) {
        User user = this.repository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(
                () -> new ResourceNotFoundException("User", "email", 1L)
        );

        return this.convertEntityToDTO(user);
    }

    //! Mapper methods ---------------------------------------------------------------------------
    private UserVO convertEntityToDTO(User entity) {
        return IbmShopMapper.parseObject(entity, UserVO.class, modelMapper);
    }

    private User convertDTOToEntity(UserVO postDTO) {
        return IbmShopMapper.parseObject(postDTO, User.class, modelMapper);
    }

    private List<UserVO> convertEntitiesToDTOs(List<User> entities) {
        return IbmShopMapper.parseListObjects(entities, UserVO.class, modelMapper);
    }

    private List<User> convertDTOsToEntities(List<UserVO> states) {
        return IbmShopMapper.parseListObjects(states, User.class, modelMapper);
    }
    //! --------------------------------------------------------------------------- Mapper methods

}
