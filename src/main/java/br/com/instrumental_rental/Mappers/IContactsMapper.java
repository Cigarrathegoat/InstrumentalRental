package br.com.instrumental_rental.Mappers;

import br.com.instrumental_rental.controller.dto.requests.ContactsDTO;
import br.com.instrumental_rental.repository.entities.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper (componentModel = "spring")
public interface IContactsMapper {

    IContactsMapper INSTANCE = Mappers.getMapper(IContactsMapper.class);

    ContactsDTO convertToDTO(Contact contact);

    Contact convertToEntity(ContactsDTO contactDTO);

    List<ContactsDTO> convertToDTOList(List<Contact> contactList);

    List<Contact> convertToEntityList(List<ContactsDTO> contactsDTOList);

}
