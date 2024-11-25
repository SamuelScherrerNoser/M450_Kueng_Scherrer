package ch.schule.bank.junit5;

import ch.schule.SalaryAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


/**
 * Tests der Klasse SalaryAccount.
 *
 * @author Luis
 * @version 1.0
 */
public class SalaryAccountTests
{
    private SalaryAccount cut;

    @BeforeEach
    public void Setup() {
        String id = "test";
        long creditLimit = -1000;
        cut = new SalaryAccount(id, creditLimit);
    }

    @AfterEach
    public void Teardown() {
        cut = null;
    }

	@Test
	public void testWithdraw_withValidInput_ShouldWithdraw()
	{
        // Arrange
        int date = 10;
        long amount = 100;

        // Act
        boolean isWithdrawn = cut.withdraw(date, amount);

        // Assert
        assert isWithdrawn;
	}

	@Test
	public void testWithdraw_withInvalidAmount_ShouldNotWithdraw()
	{
        // Arrange
        int date = 10;
        long amount = 10000;

        // Act
        boolean isWithdrawn = cut.withdraw(date, amount);

        // Assert
        assertFalse(isWithdrawn);
	}
}
