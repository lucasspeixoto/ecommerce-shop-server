package com.ibm.shop.services;

import com.ibm.shop.controllers.CountryController;
import com.ibm.shop.data.vo.CountryVO;
import com.ibm.shop.exceptions.ResourceNotFoundException;
import com.ibm.shop.mapper.CountryMapper;
import com.ibm.shop.repositories.CountryRepository;
import com.ibm.shop.utils.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class CountryService {

    private final Logger logger = Logger.getLogger(CountryService.class.getName());

    @Autowired
    private CountryRepository repository;

    @GetMapping(
            produces = {MediaType.APPLICATION_JSON}
    )
    public List<CountryVO> findAll() throws Exception {

        logger.info("Finding all countries!");

        var countries = CountryMapper.parseListObjects(repository.findAll(), CountryVO.class);

        countries
                .stream()
                .forEach(p -> p.add(linkTo(methodOn(CountryController.class).findById(p.getKey())).withSelfRel()));

        return countries;

    }

    public CountryVO findById(Long id) {
        logger.info("Finding a state by Id");

        var entity = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country", "id", id));

        CountryVO countryViewObject = CountryMapper.parseObject(entity, CountryVO.class);

        countryViewObject
                .add(linkTo(methodOn(CountryController.class)
                        .findById(id)
                ).withSelfRel());

        return countryViewObject;

    }

}
