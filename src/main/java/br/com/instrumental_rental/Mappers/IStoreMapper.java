package br.com.instrumental_rental.Mappers;

import br.com.instrumental_rental.controller.dto.requests.StoreDTO;
import br.com.instrumental_rental.repository.entities.Store;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IStoreMapper {

    IStoreMapper INSTANCE = Mappers.getMapper(IStoreMapper.class);

    StoreDTO convertToDTO(Store store);

    Store convertToEntity(StoreDTO storeDTO);

    List<StoreDTO> convertToStoreDTOList(List<Store> storeList);

    List<Store> convertToEntityList(List<StoreDTO> StoreDTOList);
}
