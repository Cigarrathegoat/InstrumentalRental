package br.com.instrumental_rental.Mappers;

import br.com.instrumental_rental.controller.dto.requests.AttendantDTO;
import br.com.instrumental_rental.repository.entities.Attendant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper (componentModel = "spring")
public interface IAttendantMapper {

    IAttendantMapper INSTANCE = Mappers.getMapper(IAttendantMapper.class);

    AttendantDTO convertToDTO(Attendant attendant);

    Attendant convertToEntity(AttendantDTO attendantDTO);

    List<AttendantDTO> convertToListDto(List<Attendant> attendantList);

    List<Attendant> convertToEntityList(List<AttendantDTO> attendantDTOList);
}
