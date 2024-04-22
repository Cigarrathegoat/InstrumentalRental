package br.com.instrumental_rental.controller.api;

import br.com.instrumental_rental.Mappers.IInstrumentMapper;
import br.com.instrumental_rental.controller.IInstrumentAPI;
import br.com.instrumental_rental.controller.dto.requests.InstrumentDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.DeleteResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.InstrumentListResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.InstrumentResponseDTO;
import br.com.instrumental_rental.exceptions.InstrumentNotFoundException;
import br.com.instrumental_rental.service.interfaces.IInstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("V1/instrument")
@RestController
public class InstrumentAPI implements IInstrumentAPI {

    private final IInstrumentService instrumentService;

    private final IInstrumentMapper instrumentMapper;

    @Autowired
    public InstrumentAPI(IInstrumentService instrumentService, IInstrumentMapper instrumentMapper) {
        this.instrumentService = instrumentService;
        this.instrumentMapper = instrumentMapper;
    }

    @PostMapping("/add")
    public InstrumentResponseDTO add(@RequestBody InstrumentDTO instrumentDTO) {
        return InstrumentResponseDTO.builder()
                .data(
                        instrumentMapper.convertToDTO(instrumentService.save(
                                instrumentMapper.convertToEntity(instrumentDTO)))
                ).build();
    }

    @PostMapping("/add_list")
    public ResponseEntity<InstrumentListResponseDTO> addList(
            @RequestBody List<InstrumentDTO> instrumentDTOList) {
        instrumentService.saveFirstTime(instrumentMapper.convertToEntityList(instrumentDTOList));

        return ResponseEntity.ok(InstrumentListResponseDTO.builder()
                .listAddedSuccessfully("Instrument added successfully").build());
    }
    @GetMapping("/find/{instrumentName}")
    public InstrumentListResponseDTO find(@PathVariable("instrumentName") String instrumentName)
            throws InstrumentNotFoundException {
        return InstrumentListResponseDTO.builder()
                .data(instrumentMapper.convertToListDTO(instrumentService.findInstrumentByMakeOrModel(instrumentName)
                )).build();
    }

    @GetMapping("/listAll")
    public InstrumentListResponseDTO listAll() {
        return InstrumentListResponseDTO.builder()
                .data(instrumentMapper.convertToListDTO(instrumentService.listAll())).build();
    }

    @PutMapping("/update/{instrumentId}")
    public InstrumentResponseDTO update(@PathVariable("instrumentId") Long instrumentID,
                                        @RequestBody InstrumentDTO instrumentDTO)
            throws InstrumentNotFoundException {
        return InstrumentResponseDTO.builder()
                .data(
                        instrumentMapper.convertToDTO(
                                instrumentService.update(
                                        instrumentMapper.convertToEntity(instrumentDTO)
                                )
                        )
                ).build();
    }

    @DeleteMapping("/delete/{instrumentId}")
    public ResponseEntity<DeleteResponseDTO> delete(@PathVariable("instrumentId") Long instrumentId)
            throws InstrumentNotFoundException {
        instrumentService.delete(instrumentId);
        return ResponseEntity.ok(DeleteResponseDTO.builder().deleteSuccessMessage("Instrument successfully deleted")
                .build());
    }
}
