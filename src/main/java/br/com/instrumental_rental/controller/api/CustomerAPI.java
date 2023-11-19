package br.com.instrumental_rental.controller.api;

import br.com.instrumental_rental.Mappers.ICustomerMapper;
import br.com.instrumental_rental.controller.ICustomerAPI;
import br.com.instrumental_rental.controller.dto.requests.AccountBalanceDTO;
import br.com.instrumental_rental.controller.dto.requests.CustomerDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.CustomerListResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.CustomerResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.DeleteResponseDTO;
import br.com.instrumental_rental.exceptions.CustomerNotFoundException;
import br.com.instrumental_rental.repository.entities.Customer;
import br.com.instrumental_rental.service.impl.CustomerService;
import br.com.instrumental_rental.service.interfaces.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("V1/customer")
public class CustomerAPI implements ICustomerAPI {

    private ICustomerService customerServiceAttribute;

    private ICustomerMapper customerMapperAttribute;

    @Autowired
    public CustomerAPI(ICustomerService customerServiceParameter,
                       ICustomerMapper customerMapperParameter) {
        this.customerServiceAttribute = customerServiceParameter;
        this.customerMapperAttribute = customerMapperParameter;
    }

    @PostMapping("/new")
    public CustomerResponseDTO add(CustomerDTO customerDTO) throws CustomerNotFoundException {

        return CustomerResponseDTO.builder()
                .data(
                        customerMapperAttribute.convertToDto(
                                customerServiceAttribute.save(
                                        customerMapperAttribute.convertToEntity(customerDTO)
                                )
                        )
                ).build();
    }

    @GetMapping("/find/{super-people}")
    public CustomerListResponseDTO find(@PathVariable("super_people") String customerName)
            throws CustomerNotFoundException {

        return CustomerListResponseDTO.builder()
                .data(customerMapperAttribute.convertoToListDto(
                                customerServiceAttribute.findCustomerByName(customerName)
                        )
                ).build();
    }



    @GetMapping("/list")
    public CustomerListResponseDTO listAll() {

        List<Customer> customerList = customerServiceAttribute.findAll();
        List<CustomerDTO> customerDTOList = customerMapperAttribute.convertoToListDto(
                customerList
        );
        return CustomerListResponseDTO.builder()
                .data(customerDTOList
                ).build();/*TODO: make this method like the one above*/

    }

    @Override
    public CustomerResponseDTO update() throws CustomerNotFoundException {
        
        return null;
    }

    @Override
    public AccountBalanceDTO addToBalance() throws CustomerNotFoundException {
        return null;
    }

    @Override
    public ResponseEntity<DeleteResponseDTO> delete(Long customerId) throws CustomerNotFoundException {
        return null;
    }
}
