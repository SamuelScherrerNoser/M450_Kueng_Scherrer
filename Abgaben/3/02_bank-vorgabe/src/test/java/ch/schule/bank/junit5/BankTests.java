package ch.schule.bank.junit5;

import ch.schule.Account;
import ch.schule.Bank;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


/**
 * Tests für die Klasse 'Bank'.
 *
 * @author Luis
 * @version 1.0
 */
public class BankTests {
    Bank cut;

    @BeforeEach
    public void Setup() {
        cut = new Bank();
    }

    @AfterEach
    public void TearDown() {
        cut = null;
    }

    @Test
    public void testCreateSavingsAccount_shouldCreateAccount() {
        // Act
        String id = cut.createSavingsAccount();
        Account actual = cut.GetAccountById(id);

        // Assert
        assertNotNull(actual);;
    }

    @Test
    public void testCreateSalaryAccount_shouldCreateAccount() {
        // Arrange
        long creditLimit = -100;

        // Act
        String id = cut.createSalaryAccount(creditLimit);
        Account actual = cut.GetAccountById(id);

        // Assert
        assertNotNull(actual);;
    }

    @Test
    public void testCreatePromoYouthAccount_shouldCreateAccount() {
        // Act
        String id = cut.createPromoYouthSavingsAccount();
        Account actual = cut.GetAccountById(id);

        // Assert
        assertNotNull(actual);;
    }

    @Test
    public void testDeposit_withValidInput_ShouldDeposit() {
        // Arrange
        String id = cut.createSavingsAccount();
        int date = 10;
        long amount = 10;

        // Act
        boolean isDeposited = cut.deposit(id, date, amount);

        // Assert
        assert isDeposited;
    }

    @Test
    public void testDeposit_withInvalidAccount_ShouldNotDeposit() {
        // Arrange
        String id = "test";
        int date = 10;
        long amount = 10;

        // Act
        boolean isDeposited = cut.deposit(id, date, amount);

        // Assert
        assertFalse(isDeposited);
    }

    @Test
    public void testWithdraw_withValidInput_ShouldWithdraw() {
        // Arrange
        int date = 10;
        long amount = 10;

        String id = cut.createSavingsAccount();
        cut.deposit(id, date, amount);

        int withdrawDate = 11;
        long withdrawAmount = 3;

        // Act
        boolean isWithdrawn = cut.withdraw(id, withdrawDate, withdrawAmount);

        // Assert
        assert isWithdrawn;
    }
}
