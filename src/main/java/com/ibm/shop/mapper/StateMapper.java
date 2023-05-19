package com.ibm.shop.mapper;

import com.ibm.shop.data.vo.StateVO;
import com.ibm.shop.entities.State;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class StateMapper {

    public static <O, D> D parseObject(O origin, Class<D> destination) {

        var mapper = new ModelMapper();

        mapper.createTypeMap(State.class, StateVO.class)
                .addMapping(State::getId, StateVO::setKey);

        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
        var mapper = new ModelMapper();

        mapper.createTypeMap(State.class, StateVO.class)
                .addMapping(State::getId, StateVO::setKey);

        List<D> destinationObjects = new ArrayList<D>();

        for (O o : origin) {
            destinationObjects.add(mapper.map(o, destination));
        }

        return destinationObjects;
    }


}

