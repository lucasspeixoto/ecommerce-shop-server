package com.ibm.shop.services;

import com.ibm.shop.data.response.StateResponse;
import com.ibm.shop.data.vo.StateVO;
import com.ibm.shop.entities.State;
import com.ibm.shop.exceptions.ResourceNotFoundException;
import com.ibm.shop.mapper.IbmShopMapper;
import com.ibm.shop.repositories.StateRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class StateService {

    private final Logger logger = Logger.getLogger(StateService.class.getName());

    @Autowired
    private StateRepository repository;

    @Autowired
    private PagedResourcesAssembler<StateVO> assembler;

    @Autowired
    private ModelMapper modelMapper;

    public StateResponse findAll(Pageable pageable) throws Exception {

        logger.info("Finding all states!");

        Page<State> pageableStates = repository.findAll(pageable);

        List<State> pageableStatesContent = pageableStates.getContent();

        List<StateVO> content = convertEntitiesToDTOs(pageableStatesContent);

        StateResponse stateResponse = new StateResponse();

        stateResponse.setContent(content);
        stateResponse.setPageNo(pageableStates.getNumber());
        stateResponse.setPageSize(pageableStates.getSize());
        stateResponse.setTotalElements(pageableStates.getTotalElements());
        stateResponse.setTotalPages(pageableStates.getTotalPages());
        stateResponse.setLast(pageableStates.isLast());

        return stateResponse;
    }

    public StateVO findById(Long id) {
        logger.info("Finding a state by Id");

        State state = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("State", "id", id));

        return this.convertEntityToDTO(state);

    }

    public List<StateVO> findByCountryCode(String code) {
        logger.info("Finding states from specific code");

        List<State> states = repository.findByCountryCode(code);

        return this.convertEntitiesToDTOs(states);

    }

    //! Mapper methods ---------------------------------------------------------------------------
    private StateVO convertEntityToDTO(State entity) {
        return IbmShopMapper.parseObject(entity, StateVO.class, modelMapper);
    }

    private State convertDTOToEntity(StateVO postDTO) {
        return IbmShopMapper.parseObject(postDTO, State.class, modelMapper);
    }

    private List<StateVO> convertEntitiesToDTOs(List<State> entities) {
        return IbmShopMapper.parseListObjects(entities, StateVO.class, modelMapper);
    }

    private List<State> convertDTOsToEntities(List<StateVO> states) {
        return IbmShopMapper.parseListObjects(states, State.class, modelMapper);
    }
    //! --------------------------------------------------------------------------- Mapper methods

}
