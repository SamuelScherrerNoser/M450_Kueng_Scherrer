package ch.schule.bank.junit5;

import ch.schule.Account;
import ch.schule.SavingsAccount;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests für die Klasse Account.
 *
 * @author Luis
 * @version 1.0
 */
public class AccountTests {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private Account cut;

    @BeforeEach
    public void Setup() {
        cut = new SavingsAccount("1");
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void Teardown() {
        cut = null;
    }

    @Test
    public void testInit_withId_shouldHaveId() {
        // Arrange
        // Act
        var id = cut.getId();
        
        // Assert
        assertEquals("1", id);
    }

    @Test
    public void testDeposit_withValidInput_ShouldAddBooking() {
        // Arrange
        int date = 10;
        int amount = 10;
        long expectedBalance = 10;

        // Act
        cut.deposit(date, amount);

        // Assert
        long actualBalance = cut.getBalance();
        assertEquals(expectedBalance, actualBalance);;
    }

    @Test
    public void testDeposit_withNegativeAmount_shouldReturnFalse() {
        // Arrange
        int date = 10;
        int amount = -10;

        // Act
        boolean isDeposited = cut.deposit(date, amount);

        // Assert
        assertFalse(isDeposited);
    }

    @Test
    public void testDeposit_withInvalidDate_shouldReturnFalse() {
        // Arrange
        int date = 10;
        int datePast = 9;
        int amount = 10;
        cut.deposit(date, amount);

        // Act
        boolean isDeposited = cut.deposit(datePast, amount);

        // Assert
        assertFalse(isDeposited);
    }

    @Test
    public void testWithdraw_withValidInput_shouldDepoistAmount() {
        // Arrange
        int depositDate = 10;
        long depositAmount = 10;
        cut.deposit(depositDate, depositAmount);

        int withdrawDate = 11;
        long withdrawAmount = 3;

        long expectedBalance = 7;
        
        // Act
        cut.withdraw(withdrawDate, withdrawAmount);
        
        // Assert
        long actualBalance = cut.getBalance();
        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    public void testWithdraw_withNegativeAmount_shouldNotDepoistAmount() {
        // Arrange
        int depositDate = 10;
        long depositAmount = 10;
        cut.deposit(depositDate, depositAmount);

        int withdrawDate = 11;
        long withdrawAmount = -3;

        long expectedBalance = 10;
        
        // Act
        cut.withdraw(withdrawDate, withdrawAmount);
        
        // Assert
        long actualBalance = cut.getBalance();
        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    public void testWithdraw_withInvalidDate_shouldNotDepoistAmount() {
        // Arrange
        int depositDate = 10;
        long depositAmount = 10;
        cut.deposit(depositDate, depositAmount);

        int withdrawDate = 9;
        long withdrawAmount = 3;

        long expectedBalance = 10;
        
        // Act
        cut.withdraw(withdrawDate, withdrawAmount);
        
        // Assert
        long actualBalance = cut.getBalance();
        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    public void testCanTransact_withEmptyBookings_shouldReturnTrue() {
        // Arrange
        int date = 10;
        
        // Act
        boolean actual = cut.canTransact(date);

        // Assert
        assert actual;
    }

    @Test
    public void testCanTransact_withInvalidDate_shouldReturnFalse() {
        // Arrange
        int date = 10;
        int amount = 1;
        cut.deposit(date, amount);

        int oldDate = 9;
        
        // Act
        boolean actual = cut.canTransact(oldDate);

        // Assert
        assertFalse(actual);
    }

    @Test
    public void testPrint()
    {
        // Arrange
        cut.deposit(10, 100);
        String expectedResult = """
Kontoauszug '1'
Datum          Betrag      Saldo
11.01.1970       0.00       0.00""";

        // Act
        cut.print();
        String actualResult = outputStreamCaptor.toString().trim();
        
        // Assert
        assertEquals(expectedResult, actualResult);

    }
    
    @Test
    public void testPrint_withArguments()
    {
        // Arrange
        cut.deposit(1, 100);
        cut.deposit(33, 100);
        cut.deposit(100, 100);

        int year = 1970;
        int month = 2;

        String expectedResult = """
Kontoauszug '1' Monat: 2.1970
Datum          Betrag      Saldo
04.02.1970       0.00       0.00""";

        // Act
        cut.print(year, month);
        String actualResult = outputStreamCaptor.toString().trim();
        
        // Assert
        assertEquals(expectedResult, actualResult);

    }
}
