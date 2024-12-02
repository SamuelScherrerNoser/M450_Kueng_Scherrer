package ch.schule.bank.junit5;

import ch.schule.AccountBalanceComparator;
import ch.schule.Account;
import ch.schule.SavingsAccount;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests für die Klasse Account.
 *
 * @author Luis
 * @version 1.0
 */
public class AccountBalanceComparatorTests {
    AccountBalanceComparator cut;

    @BeforeEach
    public void Setup() 
    {
        cut = new AccountBalanceComparator();
    }

    @AfterEach
    public void Teardown() 
    {
        cut = null;
    }

    @Test
    public void testInit()
    {
        assertNotNull(cut);
    }

    @Test
    public void testCompareObjects_withDifferentBalance_ShouldReturnMinusOne()
    {
        // Arrange
        String id_1 = "test-1";
        Account o1 = new SavingsAccount(id_1);
        o1.deposit(10, 100);

        String id_2 = "test-2";
        Account o2 = new SavingsAccount(id_2);
        o2.deposit(10, 10);


        int expectedResult = -1;

        // Act
        int actualResult = cut.compare(o1, o2);

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testCompareObjects_withDifferentBalance_ShouldReturnOne()
    {
        // Arrange
        String id_1 = "test-1";
        Account o1 = new SavingsAccount(id_1);
        o1.deposit(10, 10);

        String id_2 = "test-2";
        Account o2 = new SavingsAccount(id_2);
        o2.deposit(10, 100);


        int expectedResult = 1;

        // Act
        int actualResult = cut.compare(o1, o2);

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testCompareObjects_withSameBalance_ShouldReturnZero()
    {
        // Arrange
        String id_1 = "test-1";
        Account o1 = new SavingsAccount(id_1);
        o1.deposit(10, 10);

        String id_2 = "test-2";
        Account o2 = new SavingsAccount(id_2);
        o2.deposit(10, 10);


        int expectedResult = 0;

        // Act
        int actualResult = cut.compare(o1, o2);

        // Assert
        assertEquals(expectedResult, actualResult);
    }
}
