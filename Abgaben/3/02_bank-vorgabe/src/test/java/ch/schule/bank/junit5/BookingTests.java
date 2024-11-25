package ch.schule.bank.junit5;

import ch.schule.Booking;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    private Booking cut;

    @BeforeEach
    public void Setup() {
        int date = 10;
        int amount = 10;

        cut = new Booking(date, amount);
    }

    @AfterEach
    public void Teardown() {
        cut = null;
    }


	@Test
	public void testInitialization()
	{
        // Assert
        assertNotNull(cut);

	}
}
