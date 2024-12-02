package ch.schule.bank.junit5;

import ch.schule.Booking;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


/**
 * Tests für die Klasse Booking.
 *
 * @author Luis
 * @version 1.1
 */
public class BookingTests
{
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private Booking cut;

    @BeforeEach
    public void Setup() 
    {
        int date = 10;
        int amount = 10;

        cut = new Booking(date, amount);

        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void Teardown() 
    {
        cut = null;
    }


	@Test
	public void testInitialization()
	{
        // Assert
        assertNotNull(cut);

	}

    @Test
    public void testGetAmount()
    {
        // Arrange
        long expectedAmount = 10;

        // Act
        long actualAmount = cut.getAmount();

        // Assert
        assertEquals(expectedAmount, actualAmount);
    }

    @Test
    public void testPrint()
    {
        // Arrange
        long balance = 10_000_000;
        String expectedResult = "11.01.1970       0.00     100.00";
            
        // Act
        cut.print(balance);
        String actualResult = outputStreamCaptor.toString().trim();

        // Assert
        assertEquals(expectedResult, actualResult);
    }
}
