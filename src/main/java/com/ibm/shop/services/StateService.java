package com.ibm.shop.services;

import com.ibm.shop.controllers.StateController;
import com.ibm.shop.data.vo.StateVO;
import com.ibm.shop.exceptions.ResourceNotFoundException;
import com.ibm.shop.mapper.StateMapper;
import com.ibm.shop.repositories.StateRepository;
import com.ibm.shop.utils.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class StateService {

    private final Logger logger = Logger.getLogger(StateService.class.getName());

    @Autowired
    private StateRepository repository;

    @Autowired
    private PagedResourcesAssembler<StateVO> assembler;

    public List<StateVO> findAll() throws Exception {

        logger.info("Finding all states!");

        var states = StateMapper.parseListObjects(repository.findAll(), StateVO.class);

        states
                .stream()
                .forEach(p -> p.add(linkTo(methodOn(StateController.class).findById(p.getKey())).withSelfRel()));

        return states;
    }

    public StateVO findById(Long id) {
        logger.info("Finding a state by Id");

        var entity = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        StateVO stateViewObject = StateMapper.parseObject(entity, StateVO.class);

        stateViewObject
                .add(linkTo(methodOn(StateController.class)
                        .findById(id)
                ).withSelfRel());

        return stateViewObject;

    }

    public List<StateVO> findByCountryCode(String code) {
        logger.info("Finding states from specific code");

        var states = StateMapper.parseListObjects(repository.findByCountryCode(code), StateVO.class);

        states
                .stream()
                .forEach(p -> p.add(linkTo(methodOn(StateController.class).findById(p.getKey())).withSelfRel()));

        return states;

    }

}
