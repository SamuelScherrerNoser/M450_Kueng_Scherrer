package ch.tbz.m450.service.tests;

import ch.tbz.m450.repository.Address;
import ch.tbz.m450.repository.AddressRepository;
import ch.tbz.m450.service.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddressServiceTests {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    private Address address1;
    private Address address2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        address1 = new Address(1, "John", "Doe", "123456789", new Date(2024, Calendar.NOVEMBER, 25));
        address2 = new Address(2, "Jane", "Smith", "987654321", new Date(2023, Calendar.NOVEMBER, 25));
    }

    @Test
    void saveAddress_ValidAddress_AddressIsSaved() {
        // Arrange
        when(addressRepository.save(any(Address.class))).thenReturn(address1);

        // Act
        Address savedAddress = addressService.save(address1);

        // Assert
        assertNotNull(savedAddress, "The saved address should not be null.");
        assertEquals(address1.getFirstname(), savedAddress.getFirstname(), "The first names should match.");
        assertEquals(address1.getLastname(), savedAddress.getLastname(), "The last names should match.");

        // Verify
        verify(addressRepository, times(1)).save(any(Address.class));
    }

    @Test
    void getAllAddresses_AddressesExist_ReturnsSortedAddresses() {
        // Arrange
        when(addressRepository.findAll()).thenReturn(Arrays.asList(address2, address1));

        // Act
        List<Address> addresses = addressService.getAll();

        // Assert
        assertEquals(2, addresses.size(), "There should be 2 addresses.");
        assertEquals(address1.getLastname(), addresses.get(0).getLastname(), "The first address should be 'Doe'.");
        assertEquals(address2.getLastname(), addresses.get(1).getLastname(), "The second address should be 'Smith'.");

        // Verify
        verify(addressRepository, times(1)).findAll();
    }

    @Test
    void getAddress_AddressExists_ReturnsAddress() {
        // Arrange
        int addressId = 1;
        when(addressRepository.findById(addressId)).thenReturn(Optional.of(address1));

        // Act
        Optional<Address> foundAddress = addressService.getAddress(addressId);

        // Assert
        assertTrue(foundAddress.isPresent(), "The address should be found.");
        assertEquals(address1.getFirstname(), foundAddress.get().getFirstname(), "The first name should match.");

        // Verify
        verify(addressRepository, times(1)).findById(addressId);
    }

    @Test
    void getAddress_AddressDoesNotExist_ReturnsEmptyOptional() {
        // Arrange
        int notExistingId = 999;

        when(addressRepository.findById(notExistingId)).thenReturn(Optional.empty());

        // Act
        Optional<Address> foundAddress = addressService.getAddress(notExistingId);

        // Assert
        assertFalse(foundAddress.isPresent(), "The address with Id (" + notExistingId + ") should not be found.");

        // Verify
        verify(addressRepository, times(1)).findById(notExistingId);
    }
}