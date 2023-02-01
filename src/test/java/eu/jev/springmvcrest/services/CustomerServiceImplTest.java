package eu.jev.springmvcrest.services;

import eu.jev.springmvcrest.api.v1.mapper.CustomerMapper;
import eu.jev.springmvcrest.api.v1.model.CustomerDTO;
import eu.jev.springmvcrest.domain.Customer;
import eu.jev.springmvcrest.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class CustomerServiceImplTest {

    CustomerServiceImpl customerService;

    @Mock
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerService = new CustomerServiceImpl(customerRepository, Mappers.getMapper(CustomerMapper.class));
    }

    @Test
    void getAllCustomers() {
        Customer customer1 = Customer.builder().id(1L).firstname("Michale").lastname("Weston").build();
        Customer customer2 = Customer.builder().id(2L).firstname("Sam").lastname("Axe").build();

        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        List<CustomerDTO> customerDTOList = customerService.getAllCustomers();

        assertEquals(2, customerDTOList.size());
    }

    @Test
    void getCustomerById() {
        Customer customer = Customer.builder().id(1L).firstname("Michale").lastname("Weston").build();

        when(customerRepository.findById(anyLong())).thenReturn(Optional.ofNullable(customer));

        CustomerDTO customerDTO = customerService.getCustomerById(1L);

        assertEquals("Michale", customerDTO.getFirstname());
    }
}