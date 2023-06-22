package com.ibm.shop.services;

import com.ibm.shop.data.vo.CountryVO;
import com.ibm.shop.entities.Country;
import com.ibm.shop.exceptions.ResourceNotFoundException;
import com.ibm.shop.mapper.IbmShopMapper;
import com.ibm.shop.repositories.CountryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class CountryService {

    private final Logger logger = Logger.getLogger(CountryService.class.getName());

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CountryRepository repository;

    public List<CountryVO> findAll() throws Exception {

        logger.info("Finding all countries!");

        List<Country> countries = repository.findAll();

        return this.convertEntitiesToDTOs(countries);

    }

    public CountryVO findById(Long id) {
        logger.info("Finding a state by Id");

        Country country = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country", "id", id));

        return this.convertEntityToDTO(country);

    }

    public CountryVO create(CountryVO countryVO) {
        logger.info("Creating a new country");

        Country country = this.convertDTOToEntity(countryVO);

        Country newCountry = this.repository.save(country);

        return this.convertEntityToDTO(newCountry);
    }

    public CountryVO update(CountryVO countryVO) {
        logger.info("Updating a country");

        Country country = this.repository.findById(countryVO.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Product", "id", countryVO.getId())
        );

        country.setCode(countryVO.getCode());
        country.setName(countryVO.getName());

        Country newCountry = this.repository.save(country);

        return this.convertEntityToDTO(newCountry);
    }


    //! Mapper methods ------------------------------------------
    private CountryVO convertEntityToDTO(Country entity) {
        return IbmShopMapper.parseObject(entity, CountryVO.class, modelMapper);
    }

    private Country convertDTOToEntity(CountryVO postDTO) {
        return IbmShopMapper.parseObject(postDTO, Country.class, modelMapper);
    }

    private List<CountryVO> convertEntitiesToDTOs(List<Country> entities) {
        return IbmShopMapper.parseListObjects(entities, CountryVO.class, modelMapper);
    }

    private List<Country> convertDTOsToEntities(List<CountryVO> postDTOs) {
        return IbmShopMapper.parseListObjects(postDTOs, Country.class, modelMapper);
    }
    //! ------------------------------------------ Mapper methods

}
