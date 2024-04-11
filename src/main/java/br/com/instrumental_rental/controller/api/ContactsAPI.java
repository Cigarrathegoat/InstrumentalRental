package br.com.instrumental_rental.controller.api;

import br.com.instrumental_rental.Mappers.IContactsMapper;
import br.com.instrumental_rental.controller.IContactAPI;
import br.com.instrumental_rental.controller.dto.requests.ContactsDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.ContactsListResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.ContactsResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.DeleteResponseDTO;
import br.com.instrumental_rental.exceptions.ContactNotFoundException;
import br.com.instrumental_rental.service.interfaces.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("V1/contacts")
public class ContactsAPI implements IContactAPI{

    private final IContactService contactService;

    private final IContactsMapper contactsMapper;

    @Autowired
    public ContactsAPI(IContactService contactService, IContactsMapper contactsMapper) {
        this.contactService = contactService;
        this.contactsMapper = contactsMapper;
    }

    @Override
    public ContactsResponseDTO add(ContactsDTO contactsDTO) {
        return ContactsResponseDTO.builder()
                .data(
                        contactsMapper.convertToDTO(
                                contactService.save(
                                        contactsMapper.convertToEntity(
                                                contactsDTO)
                                )
                        )
                ).build();
    }

    @Override
    public ContactsListResponseDTO find(String holder) throws ContactNotFoundException {
        return ContactsListResponseDTO.builder().data(
                contactsMapper.convertToDTOList(contactService.findContactsByNameProvided(holder))
        ).build();
    }

    @Override
    public ContactsResponseDTO update(Long contactsId) throws ContactNotFoundException {
        return ContactsResponseDTO.builder().data(
                contactsMapper.convertToDTO(
                        contactService.update(
                                contactService.update
                                        (contactService.findById(
                                                contactsId)
                                        )
                        )
                )
        ).build();
    }

    @Override
    public ResponseEntity<DeleteResponseDTO> delete(Long contactsId) throws ContactNotFoundException {
        return null;
    }
}
