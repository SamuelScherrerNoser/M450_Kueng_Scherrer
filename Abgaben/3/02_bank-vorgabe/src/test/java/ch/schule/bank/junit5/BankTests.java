package ch.schule.bank.junit5;

import ch.schule.Account;
import ch.schule.Bank;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Tests für die Klasse 'Bank'.
 *
 * @author Luis
 * @version 1.0
 */
public class BankTests {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private Bank cut;

    @BeforeEach
    public void Setup() 
    {
        cut = new Bank();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void TearDown() 
    {
        cut = null;
    }

    @Test
    public void testCreateSavingsAccount_shouldCreateAccount() 
    {
        // Act
        String id = cut.createSavingsAccount();
        Account actual = cut.GetAccountById(id);

        // Assert
        assertNotNull(actual);;
    }

    @Test
    public void testCreateSalaryAccount_shouldCreateAccount() 
    {
        // Arrange
        long creditLimit = -100;

        // Act
        String id = cut.createSalaryAccount(creditLimit);
        Account actual = cut.GetAccountById(id);

        // Assert
        assertNotNull(actual);;
    }

    @Test
    public void testCreateSalaryAccount_WithInvalidCreditLimit_shouldCreateAccount() 
    {
        // Arrange
        long creditLimit = 100;

        // Act
        String actual = cut.createSalaryAccount(creditLimit);

        // Assert
        assertNull(actual);;
    }

    @Test
    public void testCreatePromoYouthAccount_shouldCreateAccount() 
    {
        // Act
        String id = cut.createPromoYouthSavingsAccount();
        Account actual = cut.GetAccountById(id);

        // Assert
        assertNotNull(actual);;
    }

    @Test
    public void testDeposit_withValidInput_ShouldDeposit() 
    {
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
    public void testDeposit_withInvalidAccount_ShouldNotDeposit() 
    {
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
    public void testWithdraw_withValidInput_ShouldWithdraw() 
    {
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

    @Test
    public void testWithdraw_withInvalidInput_ShouldWithdraw() 
    {
        // Arrange
        String id = "test";
        int withdrawDate = 11;
        int withdrawAmount = 100;

        // Act
        boolean isWithdrawn = cut.withdraw(id, withdrawDate, withdrawAmount);

        // Assert
        assert !isWithdrawn;
    }

    @Test
    public void testPrint_withJustId()
    {
        // Arrange
        String id = cut.createSavingsAccount();

        String expectedResult = String.format("""
Kontoauszug '%s'
Datum          Betrag      Saldo""", id);

        // Act
        cut.print(id);
        String actualResult = outputStreamCaptor.toString().trim();

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testPrint_withInvalidId()
    {
        // Arrange
        String expectedResult = "";
        String id = "test";

        // Act
        cut.print(id);
        String actualResult = outputStreamCaptor.toString().trim();

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testPrint_withDate()
    {
        // Arrange
        String id = cut.createSavingsAccount();

        int year = 1970;
        int month = 1;

        String expectedResult = String.format("""
Kontoauszug '%s' Monat: 1.1970
Datum          Betrag      Saldo""", id);

        // Act
        cut.print(id, year, month);
        String actualResult = outputStreamCaptor.toString().trim();

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testPrint_withDateAndInvalidId()
    {
        // Arrange
        String id = "test";

        int year = 1970;
        int month = 1;

        String expectedResult = "";

        // Act
        cut.print(id, year, month);
        String actualResult = outputStreamCaptor.toString().trim();

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testPrintTop5_withDate()
    {
        // Arrange
        String id = cut.createSavingsAccount();

        String expectedResult = String.format("%s: 0", id);

        // Act
        cut.printTop5();
        String actualResult = outputStreamCaptor.toString().trim();

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testPrintBottom5_withDate()
    {
        // Arrange
        String id = cut.createSavingsAccount();

        String expectedResult = String.format("%s: 0", id);

        // Act
        cut.printBottom5();
        String actualResult = outputStreamCaptor.toString().trim();

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testGetBalance_ShouldReturnBalance()
    {
        // Arrange
        String id = cut.createSavingsAccount();
        cut.deposit(id, 10, 100);

        long expected = -100; 
        
        // Act
        long actual = cut.getBalance();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testGetBalance_WithIdParameter_ShouldReturnBalance()
    {
        // Arrange
        String id = cut.createSavingsAccount();
        cut.deposit(id, 10, 100);

        long expected = 100; 
        
        // Act
        long actual = cut.getBalance(id);

        // Assert
        assertEquals(expected, actual);
    }


    @Test
    public void testGetBalance_WithInvalidId_ShouldReturnZero()
    {
        // Arrange
        String id = "test";
        long expected = 0; 
        
        // Act
        long actual = cut.getBalance(id);

        // Assert
        assertEquals(expected, actual);
    }
}
