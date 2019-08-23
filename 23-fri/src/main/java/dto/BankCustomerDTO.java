/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Martin
 */
public class BankCustomerDTO {
    private final int accountNumber;
    private String fullName ;
    double balance;

    public BankCustomerDTO(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BankCustomerDTO(entity.BankCustomer E){
        this.fullName=E.getFirstName()+" "+ E.getLastName();
        this.accountNumber=E.getId();
        this.balance=E.getBalance();
    }
    
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    
    
}
