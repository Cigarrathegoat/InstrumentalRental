package br.com.instrumental_rental.Mappers;

import br.com.instrumental_rental.controller.dto.requests.ContactsDTO;
import br.com.instrumental_rental.repository.entities.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper (componentModel = "spring")
public interface IContactsMapper {

    IContactsMapper INSTANCE = Mappers.getMapper(IContactsMapper.class);

    ContactsDTO convertToDTO(Contact contact);


}
