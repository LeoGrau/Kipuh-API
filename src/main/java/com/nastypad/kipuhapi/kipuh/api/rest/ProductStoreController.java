package com.nastypad.kipuhapi.kipuh.api.rest;

import com.nastypad.kipuhapi.kipuh.domain.model.composite.ProductStoreKey;
import com.nastypad.kipuhapi.kipuh.domain.service.ProductService;
import com.nastypad.kipuhapi.kipuh.domain.service.ProductStoreService;
import com.nastypad.kipuhapi.kipuh.mapping.mappers.ProductMapper;
import com.nastypad.kipuhapi.kipuh.mapping.mappers.ProductStoreMapper;
import com.nastypad.kipuhapi.kipuh.resource.create.CreateProductResource;
import com.nastypad.kipuhapi.kipuh.resource.create.CreateProductStoreResource;
import com.nastypad.kipuhapi.kipuh.resource.show.ProductResource;
import com.nastypad.kipuhapi.kipuh.resource.show.ProductStoreResource;
import com.nastypad.kipuhapi.kipuh.resource.update.UpdateProductResource;
import com.nastypad.kipuhapi.kipuh.resource.update.UpdateProductStoreResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/products-store", produces = "application/json")
@Tag(name = "ProductStore", description = "CRUD for products")
public class ProductStoreController {
    private final ProductStoreService productStoreService;
    private final ProductStoreMapper productStoreMapper;

    public ProductStoreController(ProductStoreService productStoreService, ProductStoreMapper productStoreMapper) {
        this.productStoreService = productStoreService;
        this.productStoreMapper = productStoreMapper;
    }

    @GetMapping("all")
    public List<ProductStoreResource> getAllSkills() {
        return productStoreMapper.toListResource(productStoreService.getAll());
    }

    @GetMapping("storeId/{storeId}/productId/{productId}")
    public ProductStoreResource getById(@PathVariable Long storeId, @PathVariable Long productId) {
        return productStoreMapper.toResource(productStoreService.getById(new ProductStoreKey(storeId, productId)));
    }

    @PostMapping
    public ProductStoreResource createProduct(@RequestBody CreateProductStoreResource productResource) {
        return productStoreMapper.toResource(productStoreService.create(productStoreMapper.createResourceToModel(productResource)));
    }

    @PutMapping("storeId/{storeId}/productId/{productId}")
    public ProductStoreResource updateProduct(@PathVariable Long storeId, @PathVariable Long productId, @RequestBody UpdateProductStoreResource updateProductResource) {
        return productStoreMapper.toResource(productStoreService.update(new ProductStoreKey(storeId, productId), productStoreMapper.updateResourceToModel(updateProductResource)));
    }

    @DeleteMapping("storeId/{storeId}/productId/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long storeId, @PathVariable Long productId) {
        return productStoreService.delete(new ProductStoreKey(storeId, productId));
    }
}
