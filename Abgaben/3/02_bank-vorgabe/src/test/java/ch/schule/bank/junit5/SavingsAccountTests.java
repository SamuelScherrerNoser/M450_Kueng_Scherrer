package ch.schule.bank.junit5;

import ch.schule.SavingsAccount;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


/**
 * Tests für die Klasse SavingsAccount.
 *
 * @author Luis
 * @version 1.0
 */
public class SavingsAccountTests
{
    private SavingsAccount cut;

    @BeforeEach
    public void Setup() {
        String id = "test";
        cut = new SavingsAccount(id);
    }

    @AfterEach
    public void Teardown() {
        cut = null;
    }

	@Test
	public void testWithdraw_WithInvalidAmount_ShouldNotWithdraw()
	{
        // Arrange
        int date = 10;
        long amount = 100;

        // Act
        boolean isWithdrawn = cut.withdraw(date, amount);

        // Assert
        assertFalse(isWithdrawn);
	}

	@Test
	public void testWithdraw_WithValidInput_ShouldWithdraw()
	{
        // Arrange
        int depositDate = 9;
        long depositAmount = 100;
        cut.deposit(depositDate, depositAmount);

        int date = 10;
        long amount = 10;


        // Act
        boolean isWithdrawn = cut.withdraw(date, amount);

        // Assert
        assert isWithdrawn;
	}
}

