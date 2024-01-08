package br.com.instrumental_rental.Mappers;

import br.com.instrumental_rental.controller.dto.requests.TheAddressDTO;
import br.com.instrumental_rental.repository.entities.TheAddress;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITheAddressMapper {

    ITheAddressMapper INSTANCE = Mappers.getMapper(ITheAddressMapper.class);

    TheAddressDTO convertToDto(TheAddress theAddress);

    TheAddress convertToEntity(TheAddressDTO theAddressDTO);

    List<TheAddressDTO> convertToListDTO(List<TheAddress> theAddressList);
}
