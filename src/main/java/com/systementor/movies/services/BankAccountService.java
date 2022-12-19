package com.systementor.movies.services;

import org.springframework.stereotype.Service;

import com.systementor.movies.model.BankAccount;
import com.systementor.movies.model.BankAccountRepository;

@Service
public class BankAccountService {
    private BankAccountRepository repository;

    public BankAccountService(BankAccountRepository repository) {
        super();
        this.repository = repository;
    }

    BankAccountError Withdraw(String accountNumber, int amount){
        if (amount < 0) return BankAccountError.NegativeAmount;
        if(amount > 3000) return BankAccountError.WithdrawalAmountTooHigh;

        var account = getAccount(accountNumber);
        if(account == null) return BankAccountError.InvalidAccount;
        if(amount > account.getSaldo()) return BankAccountError.BalanceTooLow;
        account.setSaldo(account.getSaldo()-amount);
        repository.save(account);
        return BankAccountError.None;
    }

    enum BankAccountError {
        None,
        InvalidAccount,
        BalanceTooLow,
        NegativeAmount,
        WithdrawalAmountTooHigh
      }

       BankAccount getAccount(String accountNumber) {
        for(BankAccount r : repository.findAll())
        {
            if(r.getNumber().equals(accountNumber)) return r;
        }
        return null;
    }
    
}
