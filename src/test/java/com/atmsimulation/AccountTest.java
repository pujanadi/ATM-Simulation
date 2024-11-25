package com.atmsimulation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
class AccountTest {
    List<Account> accountList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        Account account = new Account("John Doe", "012108", 100, "112233");
        accountList.add(account);
        account = new Account("Jane Doe", "932012", 30, "112244");
        accountList.add(account);
    }

    @Test
    void validateAccountNumberTest() {
        List<String> accountNumbers = Arrays.asList("123456"   // valid account number length
                ,"12345" // invalid account number length
                ,"1234567" // invalid account number length
                ,"12345A" // invalid account number alphanumeric
                ,"123$%^" // invalid account number special characters
        );

        assertTrue(Account.validateAccountNumber(accountNumbers.get(0)));
        assertFalse(Account.validateAccountNumber(accountNumbers.get(1)));
        assertFalse(Account.validateAccountNumber(accountNumbers.get(2)));
        assertFalse(Account.validateAccountNumber(accountNumbers.get(3)));
        assertFalse(Account.validateAccountNumber(accountNumbers.get(4)));
    }

}