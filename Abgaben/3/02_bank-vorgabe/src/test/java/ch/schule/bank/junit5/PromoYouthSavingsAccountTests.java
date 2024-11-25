package ch.schule.bank.junit5;

import ch.schule.PromoYouthSavingsAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

/**
 * Tests für das Promo-Jugend-Sparkonto.
 *
 * @author Luis 
 * @version 1.0
 */
public class PromoYouthSavingsAccountTests
{
    private PromoYouthSavingsAccount cut;

    @BeforeEach
    public void Setup() {
        String id = "test";

        cut = new PromoYouthSavingsAccount(id);
    }

    @AfterEach
    public void Teardown() {
        cut = null;
    }

	@Test
	public void testDeposit_withValidData_ShouldAddBonus()
	{
        // Arrange
        int date = 10;
        long amount = 100;
        long expectedBalance = 101;

        // Act
        cut.deposit(date, amount);
        long actualBalance = cut.getBalance();

        // Assert
        assertEquals(expectedBalance, actualBalance);
	}
}
