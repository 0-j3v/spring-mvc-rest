package eu.jev.springmvcrest.controllers.v1;

import eu.jev.springmvcrest.api.v1.model.CustomerDTO;
import eu.jev.springmvcrest.controllers.RestResponseEntityExceptionHandler;
import eu.jev.springmvcrest.exceptions.ResourceNotFoundException;
import eu.jev.springmvcrest.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomerControllerTest extends AbstractRestControllerTest {

    @InjectMocks
    CustomerController customerController;

    @Mock
    CustomerService customerService;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    void getAllCustomers() throws Exception {
        CustomerDTO customer1 = CustomerDTO.builder().firstname("Michale").lastname("Weston").customerUrl(CustomerController.BASE_URL + "/1").build();
        CustomerDTO customer2 = CustomerDTO.builder().firstname("Sam").lastname("Axe").customerUrl(CustomerController.BASE_URL + "/2").build();

        when(customerService.getAllCustomers()).thenReturn(Arrays.asList(customer1, customer2));

        mockMvc.perform(get(CustomerController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    void getCustomerById() throws Exception {
        CustomerDTO customer = CustomerDTO.builder().firstname("Michale").lastname("Weston").customerUrl(CustomerController.BASE_URL + "/1").build();

        when(customerService.getCustomerById(anyLong())).thenReturn(customer);

        mockMvc.perform(get(CustomerController.BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo("Michale")));
    }

    @Test
    void createNewCustomer() throws Exception {
        CustomerDTO customerDTO = CustomerDTO.builder().firstname("Michale").lastname("Weston").build();
        CustomerDTO returnDTO = CustomerDTO.builder().firstname("Michale").lastname("Weston").customerUrl(CustomerController.BASE_URL + "/1").build();

        when(customerService.createNewCustomer(any(CustomerDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(post(CustomerController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customerDTO)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstname", equalTo("Michale")))
                .andExpect(jsonPath("$.lastname", equalTo("Weston")))
                .andExpect(jsonPath("$.customer_url", equalTo(CustomerController.BASE_URL + "/1")));
    }

    @Test
    void updateCustomer() throws Exception {
        CustomerDTO customerDTO = CustomerDTO.builder().firstname("Mich").lastname("West").build();
        CustomerDTO returnDTO = CustomerDTO.builder().firstname("Mich").lastname("West").customerUrl(CustomerController.BASE_URL + "/1").build();

        when(customerService.saveCustomerByDTO(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(put(CustomerController.BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customerDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo("Mich")))
                .andExpect(jsonPath("$.lastname", equalTo("West")))
                .andExpect(jsonPath("$.customer_url", equalTo(CustomerController.BASE_URL + "/1")));

    }

    @Test
    void patchCustomer() throws Exception {
        CustomerDTO customerDTO = CustomerDTO.builder().firstname("Tom").build();
        CustomerDTO returnDTO = CustomerDTO.builder().firstname(customerDTO.getFirstname()).lastname("West").customerUrl("/api/v1/customers/1").build();

        when(customerService.patchCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(patch(CustomerController.BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customerDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo("Tom")))
                .andExpect(jsonPath("$.lastname", equalTo("West")))
                .andExpect(jsonPath("$.customer_url", equalTo(CustomerController.BASE_URL + "/1")));

    }

    @Test
    void deleteCustomer() throws Exception {
        mockMvc.perform(delete(CustomerController.BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        verify(customerService, times(1)).deleteCustomerById(anyLong());
    }

    @Test
    public void testNotFoundException() throws Exception {

        when(customerService.getCustomerById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(CustomerController.BASE_URL + "/222")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}