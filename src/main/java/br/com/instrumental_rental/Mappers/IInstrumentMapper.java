package br.com.instrumental_rental.Mappers;

import br.com.instrumental_rental.controller.dto.requests.InstrumentDTO;
import br.com.instrumental_rental.repository.entities.Instrument;
import org.aspectj.weaver.patterns.HasThisTypePatternTriedToSneakInSomeGenericOrParameterizedTypePatternMatchingStuffAnywhereVisitor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper (componentModel = "spring")
public interface IInstrumentMapper {

    IInstrumentMapper INSTANCE = Mappers.getMapper(IInstrumentMapper.class);

    InstrumentDTO convertToDTO(Instrument instrument);

    Instrument convertToEntity(InstrumentDTO instrumentDTO);

    List<InstrumentDTO> convertToListDTO(List<Instrument> instrument);

    List<Instrument> convertToEntityList(List<InstrumentDTO> instrumentDTOList);

    /*
    @Override
    public List<Instrument> convertToEntityList(List<InstrumentDTO> instrumentDTOList) {
        if (instrumentDTOList == null) {
            return null;
        }

        List<Instrument> list = new ArrayList<>( instrumentDTOList.size());

        for (InstrumentDTO instrumentDTO : instrumentDTOList) {
            list.add( convertToEntity( instrumentDTO ));
        }
        return list;
    }
     */
}
