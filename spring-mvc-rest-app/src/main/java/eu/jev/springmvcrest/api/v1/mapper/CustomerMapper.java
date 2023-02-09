package eu.jev.springmvcrest.api.v1.mapper;

import eu.jev.springmvcrest.api.v1.model.CustomerDTO;
import eu.jev.springmvcrest.domain.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    List<CustomerDTO> customerListToCustomerDTOList(List<Customer> customerList);

    CustomerDTO customerToCustomerDTO(Customer customer);

    Customer customerDTOToCustomer(CustomerDTO customerDTO);
}
