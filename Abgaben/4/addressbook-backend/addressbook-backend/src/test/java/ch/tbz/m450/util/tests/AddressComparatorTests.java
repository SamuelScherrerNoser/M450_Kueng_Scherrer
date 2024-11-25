package ch.tbz.m450.util.tests;

import ch.tbz.m450.repository.Address;
import ch.tbz.m450.util.AddressComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AddressComparatorTests {

    private AddressComparator addressComparator;

    @BeforeEach
    void setUp() {
        addressComparator = new AddressComparator();
    }

    @Test
    void compare_AddressesWithSameLastname_ReturnsZero() {
        // Arrange
        Address address1 = new Address(1, "John", "Doe", "123456789", new Date(2023, Calendar.NOVEMBER, 25));
        Address address2 = new Address(2, "Jane", "Doe", "987654321", new Date(2024, Calendar.NOVEMBER, 25));

        int expectedResult = 0;

        // Act
        int result = addressComparator.compare(address1, address2);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void compare_AddressesWithDifferentLastnames_ReturnsPositive() {
        // Arrange
        Address address1 = new Address(1, "John", "Smith", "123456789", new Date(2023, Calendar.NOVEMBER, 25));
        Address address2 = new Address(2, "Jane", "Doe", "987654321", new Date(2024, Calendar.NOVEMBER, 25));

        // Act
        int result = addressComparator.compare(address1, address2);

        // Assert
        assertTrue(result > 0);
    }

    @Test
    void compare_AddressesWithDifferentLastnames_ReturnsNegative() {
        // Arrange
        Address address1 = new Address(1, "John", "Smith", "123456789", new Date(2023, Calendar.NOVEMBER, 25));
        Address address2 = new Address(2, "Jane", "Doe", "987654321", new Date(2024, Calendar.NOVEMBER, 25));

        // Act
        int result = addressComparator.compare(address2, address1);

        // Assert
        assertTrue(result < 0);
    }

    @Test
    void compare_AddressesWithDifferentCase_ReturnsZero() {
        // Arrange
        Address address1 = new Address(1, "John", "doe", "123456789", new Date(2023, Calendar.NOVEMBER, 25));
        Address address2 = new Address(2, "Jane", "DOE", "987654321", new Date(2024, Calendar.NOVEMBER, 25));

        int expectedResult = 0;

        // Act
        int result = addressComparator.compare(address1, address2);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void compare_AddressWithNullLastname_ThrowsNullPointerException() {
        // Arrange
        Address address1 = new Address(1, "John", null, "123456789", new Date(2023, Calendar.NOVEMBER, 25));
        Address address2 = new Address(2, "Jane", "Doe", "987654321", new Date(2024, Calendar.NOVEMBER, 25));

        // Act & Assert
        assertThrows(NullPointerException.class, () -> addressComparator.compare(address1, address2));
    }
}