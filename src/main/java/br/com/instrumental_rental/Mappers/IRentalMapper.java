package br.com.instrumental_rental.Mappers;

import br.com.instrumental_rental.controller.dto.requests.RentalDTO;
import br.com.instrumental_rental.repository.entities.Rental;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IRentalMapper {

    IRentalMapper INSTANCE = Mappers.getMapper(IRentalMapper.class);
    RentalDTO convertToDTO(Rental rental);

    Rental convertToEntity(RentalDTO rentalDTO);

    List<RentalDTO> convertToListDto(List<Rental> rentalList);

}
