package br.com.instrumental_rental.controller.api;

import br.com.instrumental_rental.Mappers.IStoreMapper;
import br.com.instrumental_rental.controller.IStoreAPI;
import br.com.instrumental_rental.controller.dto.requests.StoreDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.StoreResponseDTO;
import br.com.instrumental_rental.exceptions.StoreNotFoundException;
import br.com.instrumental_rental.service.interfaces.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/store")
public class StoreAPI implements IStoreAPI {

    IStoreService storeService;

    IStoreMapper storeMapper;

    @Autowired
    public StoreAPI(IStoreService storeService, IStoreMapper storeMapper) {
        this.storeService = storeService;
        this.storeMapper = storeMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<StoreResponseDTO> add(@RequestBody StoreDTO storeDTO)
            throws StoreNotFoundException {
        storeService.save(storeMapper.convertToEntity(storeDTO));
        return ResponseEntity.ok(StoreResponseDTO.builder()
                .saveSuccessMessage("Store saved successfullyy").build());
    }

}
