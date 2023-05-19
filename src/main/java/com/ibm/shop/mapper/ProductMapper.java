package com.ibm.shop.mapper;

import com.ibm.shop.data.vo.ProductVO;
import com.ibm.shop.entities.Product;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper {

    public static <O, D> D parseObject(O origin, Class<D> destination, ModelMapper mapper) {

        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination, ModelMapper mapper) {

        List<D> destinationObjects = new ArrayList<D>();

        for (O o : origin) {
            destinationObjects.add(mapper.map(o, destination));
        }

        return destinationObjects;
    }


}

