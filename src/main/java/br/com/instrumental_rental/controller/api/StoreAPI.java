package br.com.instrumental_rental.controller.api;

import br.com.instrumental_rental.Mappers.IStoreMapper;
import br.com.instrumental_rental.controller.IStoreAPI;
import br.com.instrumental_rental.controller.dto.requests.StoreDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.DeleteResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.StoreListResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.StoreResponseDTO;
import br.com.instrumental_rental.exceptions.AttendantNotFoundException;
import br.com.instrumental_rental.exceptions.CustomerNotFoundException;
import br.com.instrumental_rental.exceptions.InstrumentNotFoundException;
import br.com.instrumental_rental.exceptions.StoreNotFoundException;
import br.com.instrumental_rental.service.interfaces.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("V1/store")
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
                .saveSuccessMessage("Store saved successfully").build());
    }

    @PostMapping("/add_list")
    public ResponseEntity<StoreListResponseDTO> addList(@RequestBody List<StoreDTO> storeListDTO)
            throws CustomerNotFoundException, InstrumentNotFoundException,
            AttendantNotFoundException, StoreNotFoundException {
        storeService.saveFirstTime(storeMapper.convertToEntityList(storeListDTO));
        return ResponseEntity.ok(StoreListResponseDTO.builder()
                .addListMessage("List successfully added").build());
    }

    @GetMapping("/find/{name}")
    public StoreResponseDTO find(@PathVariable("name") String name)
            throws StoreNotFoundException {
        return StoreResponseDTO.builder()
                .data(storeMapper.convertToDTO(storeService.findByName(name)
                        )
                ).build();
    }

    @PutMapping("/update/{storeId}")
    public StoreResponseDTO update(@PathVariable("storeId") Long storeId,
                                   @RequestBody StoreDTO storeDTO)
            throws StoreNotFoundException {
        return StoreResponseDTO.builder()
                .data(storeMapper.convertToDTO(
                                storeService.update(
                                        storeMapper.convertToEntity(storeDTO)
                                )
                        )
                ).build();
    }

    @DeleteMapping("/delete/{storeId}")
    public ResponseEntity<DeleteResponseDTO> delete(@PathVariable("storeId") Long storeId)
        throws StoreNotFoundException {
        storeService.delete(storeId);
        return ResponseEntity.ok(DeleteResponseDTO.builder().
                deleteSuccessMessage("Store deleted successfully").build());
    }

}
