package com.ibm.shop.controllers;
import com.ibm.shop.data.vo.ProductCategoryVO;
import com.ibm.shop.services.ProductCategoryService;
import com.ibm.shop.utils.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/categories")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService service;

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<ProductCategoryVO>>> findAll(Pageable pageable) throws Exception {

        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping(
            value = "/{id}",
            produces = {MediaType.APPLICATION_JSON}
    )
    public ResponseEntity<ProductCategoryVO> findById(@PathVariable Long id) {
        ProductCategoryVO selectedCategory = service.findById(id);

        return ResponseEntity.ok().body(selectedCategory);
    }
}
