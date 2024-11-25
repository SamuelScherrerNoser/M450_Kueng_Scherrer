package ch.tbz.m450.repository.tests;

import ch.tbz.m450.repository.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AddressTests {

    private Address address;

    @BeforeEach
    void setUp() {
        address = new Address(1, "John", "Doe", "123456789", new Date(2024, Calendar.NOVEMBER, 25));
    }

    @Test
    void constructor_ValidAddress_FieldsAreCorrectlyInitialized() {
        // Arrange
        int expectedId = 1;
        String expectedFirstname = "John";
        String expectedLastname = "Doe";
        String expectedPhonenumber = "123456789";

        // Act

        // Assert
        assertNotNull(address);
        assertEquals(expectedId, address.getId());
        assertEquals(expectedFirstname, address.getFirstname());
        assertEquals(expectedLastname, address.getLastname());
        assertEquals(expectedPhonenumber, address.getPhonenumber());
        assertNotNull(address.getRegistrationDate());
    }

    @Test
    void setFirstname_ValidFirstname_FirstnameIsUpdated() {
        // Arrange
        String newFirstname = "Jane";

        // Act
        address.setFirstname(newFirstname);

        // Assert
        assertEquals(newFirstname, address.getFirstname());
    }

    @Test
    void setLastname_ValidLastname_LastnameIsUpdated() {
        // Arrange
        String newLastname = "Smith";

        // Act
        address.setLastname(newLastname);

        // Assert
        assertEquals(newLastname, address.getLastname());
    }

    @Test
    void setPhonenumber_ValidPhonenumber_PhonenumberIsUpdated() {
        // Arrange
        String newNumber = "079 000 00 00";

        // Act
        address.setPhonenumber(newNumber);

        // Assert
        assertEquals(newNumber, address.getPhonenumber());
    }

    @Test
    void setRegistrationDate_ValidDate_RegistrationDateIsUpdated() {
        // Arrange
        Date newDate = new Date(2020, Calendar.JULY, 23);

        // Act
        address.setRegistrationDate(newDate);

        // Assert
        assertEquals(newDate, address.getRegistrationDate());
    }
}