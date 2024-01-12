package br.com.instrumental_rental.controller.api;

import br.com.instrumental_rental.Mappers.ICustomerMapper;
import br.com.instrumental_rental.controller.ICustomerAPI;
import br.com.instrumental_rental.controller.dto.requests.AccountBalanceDTO;
import br.com.instrumental_rental.controller.dto.requests.CustomerDTO;
import br.com.instrumental_rental.controller.dto.responses.errors.ErrorResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.errors.ErrorSpecificationDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.AccountBalanceResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.CustomerListResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.CustomerResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.DeleteResponseDTO;
import br.com.instrumental_rental.exceptions.CustomerNotFoundException;
import br.com.instrumental_rental.exceptions.WithdrawalGreaterThanBalanceException;
import br.com.instrumental_rental.repository.entities.Customer;
import br.com.instrumental_rental.service.interfaces.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
    public CustomerResponseDTO add(CustomerDTO customerDTO) {

        return CustomerResponseDTO.builder()
                .data(
                        customerMapperAttribute.convertToDto(
                                customerServiceAttribute.save(
                                        customerMapperAttribute.convertToEntity(customerDTO)
                                )
                        )
                ).build();
    }

    //TODO ask Segundo what exactly happens in this method
    @GetMapping("/find/{numberProvided}")
    public CustomerResponseDTO find(@PathVariable("numberProvided") String numberProvided)
            throws CustomerNotFoundException {

        return CustomerResponseDTO.builder()
                .data(customerMapperAttribute.convertToDto(
                                customerServiceAttribute
                                        .findCustomerByNumberProvided(numberProvided)
                        )
                ).build();
    }

    @GetMapping("/list")
    public CustomerListResponseDTO listAll() {

        return CustomerListResponseDTO.builder()
                .data(customerMapperAttribute.convertoToListDto(
                                customerServiceAttribute.findAll()
                        )
                )
                .build();/*TODO: make this method like the one above*/

    }

    @PutMapping("/update/{customerId}")
    public CustomerResponseDTO update(@PathVariable("customerId") Long customerId,
                                      @RequestBody CustomerDTO customerDTO) throws CustomerNotFoundException {

        return CustomerResponseDTO.builder()
                .data(customerMapperAttribute.convertToDto(customerServiceAttribute.update(
                                        customerMapperAttribute.convertToEntity(customerDTO)
                                )
                        )
                )
                .build();
    }

    @PutMapping("/add-to-balance/{customerId}/{amount}")
    public AccountBalanceResponseDTO addToBalance(@PathVariable("customerId") Long customerId,
                                                  @PathVariable("amount") BigDecimal amount)
            throws CustomerNotFoundException {
        return AccountBalanceResponseDTO.builder()
                .data(
                        customerServiceAttribute.addToBalance(customerId,
                                amount)
                ).build();
    }

    @PutMapping("/withdraw/{customerId}/{value}")
    public AccountBalanceResponseDTO withdraw(@PathVariable("customerId") Long customerId,
                                              @PathVariable BigDecimal value)
            throws CustomerNotFoundException, WithdrawalGreaterThanBalanceException {
        return AccountBalanceResponseDTO.builder()
                .data(
                        customerServiceAttribute.withdraw(customerId,
                                value
                        )
                ).build();
    }
    /*
    @PutMapping("/add-to-balance/{customerId}")
    public AccountBalanceResponseDTO addToBalance(@PathVariable("customerId") Long customerId,
                                                  @RequestBody AccountBalanceDTO accountBalanceDTO)
            throws CustomerNotFoundException {
        return AccountBalanceResponseDTO.builder()
                .data(
                        customerMapperAttribute.convertAccountBalanceToDTO(
                                customerServiceAttribute.addToBalance(customerId,
                                BigDecimal.valueOf(accountBalanceDTO.getValueToAddOrWithdraw())))
                ).build();
    }

     @PutMapping("/withdraw/{customerId}")
    public AccountBalanceResponseDTO withdraw(@PathVariable("customerId") Long customerId,
                                              @RequestBody AccountBalanceDTO accountBalanceDTO)
        throws CustomerNotFoundException, WithdrawalGreaterThanBalanceException {
        return AccountBalanceResponseDTO.builder()
                .data(
                        customerMapperAttribute.convertAccountBalanceToDTO(
                                customerServiceAttribute.withdraw(customerId,
                                        BigDecimal.valueOf(accountBalanceDTO.getValueToAddOrWithdraw()))
                        )
                ).build();
    }
     */


    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<DeleteResponseDTO> delete(@PathVariable("customerId") Long customerId)
            throws CustomerNotFoundException {
        customerServiceAttribute.delete(customerId);
        return ResponseEntity.ok(DeleteResponseDTO.builder().deleteSuccessMessage("customer deleted")
                .build());
    }
}
