package br.com.instrumental_rental.Mappers;

import br.com.instrumental_rental.controller.dto.requests.CustomerDTO;
import br.com.instrumental_rental.repository.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface ICustomerMapper {

    ICustomerMapper INSTANCE = Mappers.getMapper(ICustomerMapper.class);

    CustomerDTO convertToDto(Customer customer);

    Customer convertToEntity(CustomerDTO customerDTO);

    List<CustomerDTO> convertoToListDto(List<Customer>customerList);
}
