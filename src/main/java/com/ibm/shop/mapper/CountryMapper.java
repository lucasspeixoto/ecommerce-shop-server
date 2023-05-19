package com.ibm.shop.mapper;

import com.ibm.shop.data.vo.CountryVO;
import com.ibm.shop.entities.Country;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class CountryMapper {

    public static <O, D> D parseObject(O origin, Class<D> destination) {

        var mapper = new ModelMapper();

        mapper.createTypeMap(Country.class, CountryVO.class)
                .addMapping(Country::getId, CountryVO::setKey);

        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
        var mapper = new ModelMapper();

        mapper.createTypeMap(Country.class, CountryVO.class)
                .addMapping(Country::getId, CountryVO::setKey);

        List<D> destinationObjects = new ArrayList<D>();

        for (O o : origin) {
            destinationObjects.add(mapper.map(o, destination));
        }

        return destinationObjects;
    }


}

