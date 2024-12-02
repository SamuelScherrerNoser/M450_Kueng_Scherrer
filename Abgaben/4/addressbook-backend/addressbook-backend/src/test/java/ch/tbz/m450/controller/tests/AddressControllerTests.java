package ch.tbz.m450.controller.tests;

import ch.tbz.m450.controller.AddressController;
import ch.tbz.m450.repository.Address;
import ch.tbz.m450.service.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AddressControllerTests {

    @Mock
    private AddressService addressService;

    @InjectMocks
    private AddressController addressController;

    private MockMvc mockMvc;

    private Address address1;
    private Address address2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(addressController).build();

        address1 = new Address(1, "John", "Doe", "123456789", new Date(2024, Calendar.NOVEMBER, 25));
        address2 = new Address(2, "Jane", "Smith", "987654321", new Date(2023, Calendar.NOVEMBER, 25));
    }

    @Test
    void createAddress_ValidAddress_ReturnsCreated() throws Exception {
        // Arrange
        String url = "/address";
        String expectedFirstname = "John";
        String expectedLastname = "Doe";

        when(addressService.save(any(Address.class))).thenReturn(address1);

        // Act & Assert
        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1, \"firstname\":\"John\", \"lastname\":\"Doe\", \"phonenumber\":\"123456789\", \"registrationDate\":\"2024-11-25\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstname").value(expectedFirstname))
                .andExpect(jsonPath("$.lastname").value(expectedLastname));

        // Verify
        verify(addressService, times(1)).save(any(Address.class));
    }

    @Test
    void getAddresses_AddressesExist_ReturnsList() throws Exception {
        // Arrange
        String url = "/address";
        String expectedAddress1Firstname = "John";
        String expectedAddress2Firstname = "Jane";

        when(addressService.getAll()).thenReturn(Arrays.asList(address1, address2));

        // Act & Assert
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstname").value(expectedAddress1Firstname))
                .andExpect(jsonPath("$[1].firstname").value(expectedAddress2Firstname));

        // Verify
        verify(addressService, times(1)).getAll();
    }

    @Test
    void getAddress_AddressExists_ReturnsAddress() throws Exception {
        // Arrange
        int addressId = 1;
        String url = "/address/{address_id}";
        String expectedFirstname = "John";
        String expectedLastname = "Doe";

        when(addressService.getAddress(addressId)).thenReturn(Optional.of(address1));

        // Act & Assert
        mockMvc.perform(get(url, addressId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname").value(expectedFirstname))
                .andExpect(jsonPath("$.lastname").value(expectedLastname));

        // Verify
        verify(addressService, times(1)).getAddress(addressId);
    }

    @Test
    void getAddress_AddressDoesNotExist_ReturnsNotFound() throws Exception {
        // Arrange
        int notExistingId = 999;
        String url = "/address/{address_id}";

        when(addressService.getAddress(notExistingId)).thenReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(get(url, notExistingId))
                .andExpect(status().isNotFound());

        // Verify
        verify(addressService, times(1)).getAddress(notExistingId);
    }
}