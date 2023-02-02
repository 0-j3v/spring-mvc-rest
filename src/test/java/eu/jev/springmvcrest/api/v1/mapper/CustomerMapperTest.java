package eu.jev.springmvcrest.api.v1.mapper;

import eu.jev.springmvcrest.api.v1.model.CustomerDTO;
import eu.jev.springmvcrest.domain.Customer;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerMapperTest {

    public static final String FIRSTNAME = "Jimmy";
    public static final String LASTNAME = "Fallon";
    CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);

    @Test
    void customerListToCustomerDTOList() {
        Customer customer = Customer.builder().firstname(FIRSTNAME).lastname(LASTNAME).build();
        List<Customer> customerList = Arrays.asList(customer, customer);

        List<CustomerDTO> customerDTOList = customerMapper.customerListToCustomerDTOList(customerList);

        assertEquals(2, customerDTOList.size());
    }

    @Test
    void customerToCustomerDTO() {
        Customer customer = Customer.builder().firstname(FIRSTNAME).lastname(LASTNAME).build();

        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        assertEquals(FIRSTNAME, customerDTO.getFirstname());
        assertEquals(LASTNAME, customerDTO.getLastname());
    }

    @Test
    void customerDTOToCustomer() {
        CustomerDTO customerDTO = CustomerDTO.builder().firstname(FIRSTNAME).lastname(LASTNAME).build();

        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);

        assertEquals(FIRSTNAME, customer.getFirstname());
        assertEquals(LASTNAME, customer.getLastname());
    }
}