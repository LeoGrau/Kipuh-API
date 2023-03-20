package com.nastypad.kipuhapi.kipuh.api.rest;

import com.nastypad.kipuhapi.kipuh.domain.service.StoreService;
import com.nastypad.kipuhapi.kipuh.mapping.mappers.StoreMapper;
import com.nastypad.kipuhapi.kipuh.resource.create.CreateStoreResource;
import com.nastypad.kipuhapi.kipuh.resource.show.StoreResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/stores", produces = "application/json")
@Tag(name = "Store", description = "CRUD for stores")
public class StoreController {
    private final StoreService storeService;
    private final StoreMapper storeMapper;

    public StoreController(StoreService storeService, StoreMapper storeMapper) {
        this.storeService = storeService;
        this.storeMapper = storeMapper;
    }

    @GetMapping("all")
    public List<StoreResource> getAll() {
        return storeMapper.toListResource(storeService.getAll());
    }
    @GetMapping("{id}")
    public StoreResource getById(@PathVariable Long id) {
        return storeMapper.toResource(storeService.getById(id));
    }
    @PostMapping()
    public StoreResource createStore(@RequestBody CreateStoreResource createStoreResource) {
        return storeMapper.toResource(storeService.create(storeMapper.createResourceToModel(createStoreResource)));
    }
}
