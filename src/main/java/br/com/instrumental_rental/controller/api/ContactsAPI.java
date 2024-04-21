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
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/add")
    public ContactsResponseDTO add(@RequestBody ContactsDTO contactsDTO) {
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

/* @PostMapping("/new_list")
    public ResponseEntity<AttendantListResponseDTO> addList(
            @RequestBody List<AttendantDTO> attendantDTOList
    ) {
       attendantService.saveFirstTime(attendantMapper.convertToEntityList(attendantDTOList));
       return ResponseEntity.ok(
               AttendantListResponseDTO.builder()
                       .addListSuccessMessage(
                               "List successfully added"
                       )
                       .build()
       );
    }

 */

    @PostMapping("/new_list")
    public ResponseEntity<ContactsListResponseDTO> addList(
            @RequestBody List<ContactsDTO> contactsDTOList
            ) {
        contactService.saveFirstTime(contactsMapper.convertToEntityList(contactsDTOList));
        return ResponseEntity.ok(
                ContactsListResponseDTO.builder()
                        .addListSuccessMessage(
                                "List successfully added"
                        )
                        .build()
        );
    }

    @GetMapping("/find/{holder}")
    public ContactsListResponseDTO find(@PathVariable("holder")String holder) throws ContactNotFoundException {
        return ContactsListResponseDTO.builder().data(
                contactsMapper.convertToDTOList(contactService.findContactsByNameProvided(holder))
        ).build();
    }

    @PutMapping("/update/{contactsId}")
    public ContactsResponseDTO update(@PathVariable("contactsId") Long contactsId,
                                      @RequestBody ContactsDTO contactsDTO) throws ContactNotFoundException {
        return ContactsResponseDTO.builder().data(
                contactsMapper.convertToDTO(
                        contactService.update(
                                        (contactService.findById(
                                                contactsId)
                                        )
                        )
                )
        ).build();
    }

    /*
     @DeleteMapping("/{attendantId}")
    public ResponseEntity<DeleteResponseDTO> delete(@PathVariable("attendantId") Long attendantId)
            throws AttendantNotFoundException {
        attendantService.delete(attendantId);
        return ResponseEntity.ok(DeleteResponseDTO
                .builder()
                .deleteSuccessMessage("Attendant successfully deleted")
                .build());
    }
}
     */

    @DeleteMapping("/delete/{contactId}")
    public ResponseEntity<DeleteResponseDTO> delete(@PathVariable("contactId") Long contactsId)
            throws ContactNotFoundException {
        contactService.delete(contactsId);
        return ResponseEntity.ok(DeleteResponseDTO.builder()
                .deleteSuccessMessage("Contact successfully deleted")
                .build());
    }
}
