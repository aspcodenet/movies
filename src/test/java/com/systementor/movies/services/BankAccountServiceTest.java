package com.systementor.movies.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.systementor.movies.model.BankAccount;
import com.systementor.movies.model.BankAccountRepository;
import com.systementor.movies.services.BankAccountService.BankAccountError;


public class BankAccountServiceTest {
    private BankAccountService sut;
    private BankAccountRepository bankAccountRepository;

    @BeforeEach
    void initService(){
        bankAccountRepository = mock(BankAccountRepository.class);
        sut = new BankAccountService(bankAccountRepository);
    }

    @Test
    void should_give_error_when_nonexisting_account() {
        //ARRANGE
        int amount = 100;
        String kontoNummer = "12345";

        //ACT
        var result = sut.Withdraw(kontoNummer, amount);

        //ASSERT
        assertEquals(BankAccountError.InvalidAccount, result);

    }


    @Test
    void should_give_error_when_withdrawing_more_than_balance() {
        //ARRANGE
        int amount = 100;
        String kontoNummer = "12345";
        var accounts = new ArrayList<BankAccount>();
        var account = new BankAccount("12345");
        account.setSaldo(90);
        accounts.add(account);

        when(bankAccountRepository.findAll()).thenReturn(accounts);

        //ACT
        var result = sut.Withdraw(kontoNummer, amount);

        //ASSERT
        assertEquals(BankAccountError.BalanceTooLow, result);

    }


    @Test
    void should_return_ok_when_withdrawing_less_than_balance() {
        //ARRANGE
        int amount = 100;
        String kontoNummer = "12345";
        var accounts = new ArrayList<BankAccount>();
        var account = new BankAccount("12345");
        account.setSaldo(199);
        accounts.add(account);

        when(bankAccountRepository.findAll()).thenReturn(accounts);

        //ACT
        var result = sut.Withdraw(kontoNummer, amount);

        //ASSERT
        assertEquals(BankAccountError.None, result);
        assertEquals(99, account.getSaldo());

    }


    @Test
    void should_savetodatabase_whenwithdrawing_less_than_balance() {
        //ARRANGE
        int amount = 100;
        String kontoNummer = "12345";
        var accounts = new ArrayList<BankAccount>();
        var account = new BankAccount("12345");
        account.setSaldo(199);
        accounts.add(account);

        when(bankAccountRepository.findAll()).thenReturn(accounts);

        //ACT
        var result = sut.Withdraw(kontoNummer, amount);

        //ASSERT
        //verify(bankAccountRepository,times(1)).save(any());

        verify(bankAccountRepository,times(1)).save(account);
        
    }






    @Test
    void should_give_error_when_withdrawal_with_negative_amount() {
        //ARRANGE
        int amount = -1;
        String kontoNummer = "12345";

        //ACT
        var result = sut.Withdraw(kontoNummer, amount);

        //ASSERT
        assertEquals(BankAccountError.NegativeAmount, result);
    }


    @Test
    void should_give_error_when_withdrawal_more_than_3000() {
        //ARRANGE
        int amount = 4500;
        String kontoNummer = "12345";

        //ACT
        var result = sut.Withdraw(kontoNummer, amount);

        //ASSERT
        assertEquals(BankAccountError.WithdrawalAmountTooHigh, result);
    }


}
