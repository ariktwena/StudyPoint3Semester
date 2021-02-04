/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.BankCustomer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tha
 */
public class BankCustomerDTO {
    private long CustomerID;
    private String fullName;
    private String accountNumberDTO;
    private double balanceDTO;

    public BankCustomerDTO(long CustomerID, String fullName, String accountNumberDTO, double balanceDTO) {
        this.CustomerID = CustomerID;
        this.fullName = fullName;
        this.accountNumberDTO = accountNumberDTO;
        this.balanceDTO = balanceDTO;
    }
    
     public BankCustomerDTO(BankCustomer bankCustomer) {
        this.CustomerID = bankCustomer.getId();
        this.fullName = bankCustomer.getFirstname() + " " + bankCustomer.getLastname();
        this.accountNumberDTO = bankCustomer.getAccountNumber();
        this.balanceDTO = bankCustomer.getBalance();
    }

    public long getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(long CustomerID) {
        this.CustomerID = CustomerID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAccountNumberDTO() {
        return accountNumberDTO;
    }

    public void setAccountNumberDTO(String accountNumberDTO) {
        this.accountNumberDTO = accountNumberDTO;
    }

    public double getBalanceDTO() {
        return balanceDTO;
    }

    public void setBalanceDTO(double balanceDTO) {
        this.balanceDTO = balanceDTO;
    }

    @Override
    public String toString() {
        return "BankCustomerDTO{" + "CustomerID=" + CustomerID + ", fullName=" + fullName + ", accountNumberDTO=" + accountNumberDTO + ", balanceDTO=" + balanceDTO + '}';
    }
    
    public static List<BankCustomerDTO> getAllBankCustomerDTOs(List<BankCustomer> bankCustomers){
        List<BankCustomerDTO> bankCustomerDTOs = new ArrayList();
        bankCustomers.forEach(BankCustomers->bankCustomerDTOs.add(new BankCustomerDTO(BankCustomers)));
        return bankCustomerDTOs;
    }
    
}
