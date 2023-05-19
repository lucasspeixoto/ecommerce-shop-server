package com.ibm.shop.mapper;

import com.ibm.shop.data.vo.ProductCategoryVO;
import com.ibm.shop.entities.ProductCategory;
import org.modelmapper.ModelMapper;

public class ProductCategoryMapper {

    public static <O, D> D parseObject(O origin, Class<D> destination) {

        var mapper = new ModelMapper();

        mapper.createTypeMap(ProductCategory.class, ProductCategoryVO.class)
                .addMapping(ProductCategory::getId, ProductCategoryVO::setKey);

        return mapper.map(origin, destination);
    }


}

