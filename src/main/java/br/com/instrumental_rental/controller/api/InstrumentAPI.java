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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("V1/instrument")
@RestController
public class InstrumentAPI implements IInstrumentAPI {

    private IInstrumentService instrumentService;

    private IInstrumentMapper instrumentMapper;

    @Autowired
    public InstrumentAPI(IInstrumentService instrumentService, IInstrumentMapper instrumentMapper) {
        this.instrumentService = instrumentService;
        this.instrumentMapper = instrumentMapper;
    }

    @PostMapping("/add")
    public InstrumentResponseDTO add(InstrumentDTO instrumentDTO) {
        return InstrumentResponseDTO.builder()
                .data(
                        instrumentMapper.convertToDTO(instrumentService.save(instrumentMapper.convertTo))
                ).build();
    }

    @Override
    public InstrumentResponseDTO find(String instrumentName) throws InstrumentNotFoundException {
        return null;
    }

    @Override
    public InstrumentListResponseDTO findAll() {
        return null;
    }

    @Override
    public InstrumentResponseDTO update(String instrumentID, InstrumentDTO instrumentDTO) throws InstrumentNotFoundException {
        return null;
    }

    @Override
    public ResponseEntity<DeleteResponseDTO> delete(String instrumentId) throws InstrumentNotFoundException {
        return null;
    }
}
