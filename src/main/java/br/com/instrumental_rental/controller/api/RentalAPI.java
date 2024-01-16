package br.com.instrumental_rental.controller.api;

import br.com.instrumental_rental.Mappers.IRentalMapper;
import br.com.instrumental_rental.controller.IRentalAPI;
import br.com.instrumental_rental.controller.dto.requests.RentalDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.DeleteResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.RentalListResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.RentalResponseDTO;
import br.com.instrumental_rental.exceptions.*;
import br.com.instrumental_rental.service.interfaces.IRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("V1/rental")
public class RentalAPI implements IRentalAPI {

    IRentalService rentalService;

    IRentalMapper rentalMapper;

    @Autowired
    public RentalAPI(IRentalService rentalService, IRentalMapper rentalMapper) {
        this.rentalService = rentalService;
        this.rentalMapper = rentalMapper;
    }

    @PostMapping("/add")
    public RentalResponseDTO add(@RequestBody RentalDTO rentalDTO)
            throws CustomerNotFoundException, InstrumentNotFoundException,
            AttendantNotFoundException, WithdrawalGreaterThanBalanceException,
            EndDateNotAfterStartDateException {
        return RentalResponseDTO.builder().data(
                rentalMapper.convertToDTO(rentalService.save(rentalMapper.convertToEntity(rentalDTO)
                        )
                )
        ).build();
    }

    @GetMapping("/find/{keyword}")
    public RentalListResponseDTO find(@PathVariable("rentalId") String keyWord) throws RentalNotFoundException {
        return RentalListResponseDTO.builder()
                .data(
                        rentalMapper.convertToListDto(rentalService.findRentalListByWord(keyWord))
                ).build();
    }

    @PostMapping("/listAll")
    public RentalListResponseDTO findAll() {
        return RentalListResponseDTO.builder()
                .data(
                        rentalMapper.convertToListDto(rentalService.findAll())
                ).build();
    }

    @PutMapping("/update/{rentalId}")
    public RentalResponseDTO update(@PathVariable("rentalId") Long rentalId,
                                    @RequestBody RentalDTO rentalDTO) throws RentalNotFoundException {
        return RentalResponseDTO.builder()
                .data(
                        rentalMapper.convertToDTO(rentalService.update(rentalMapper.convertToEntity(rentalDTO)))
                ).build();
    }

    @Override
    public ResponseEntity<DeleteResponseDTO> delete(String rentalId) throws RentalNotFoundException {
        return null;
    }
}
