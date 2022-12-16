package com.systementor.movies.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BankAccount {

    private int saldo;

    public BankAccount(String accountNumber) {
        super();
        number = accountNumber;
        saldo = 0;
    }

    public Integer getSaldo() {
        return saldo;
    }
    public void setSaldo(Integer newSaldo) {
        saldo = newSaldo;
    }



    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String number;
    public Integer getId() {
        return id;
      }
      public void setId(Integer newId) {
        id = newId;
      }
    
      public String getNumber()
      {
          return number;
      }
      public void setNumber(String newNumber)
      {
          number = newNumber;
      }

    
}
