package ch.schule.bank.junit5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ch.schule.BankUtils;

/**
 * Tests für die Klasse 'Bank'.
 *
 * @author Luis
 * @version 1.0
 */
public class BankUtilsTests {

    @Test
    public void testFormatBankDate()
    {
        // Arrange
        int date = 10;
        String expectedResult = "11.01.1970";

        // Act
        String actualResult = BankUtils.formatBankDate(date);

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testFormatAmount()
    {
        // Arrange
        long amount = 10_000_000;
        String expectedResult = "    100.00";

        // Act
        String actualResult = BankUtils.formatAmount(amount);

        // Assert
        assertEquals(expectedResult, actualResult);
    }
}
