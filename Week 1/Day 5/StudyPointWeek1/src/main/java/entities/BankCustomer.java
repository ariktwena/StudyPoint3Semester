package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "BankCustomer.deleteAllRows", query = "DELETE from BankCustomer")
public class BankCustomer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO, delete this class, or rename to an Entity class that makes sense for what you are about to do
    // Delete EVERYTHING below if you decide to use this class, it's dummy data used for the initial demo
    private String firstname;
    private String lastname;
    private String accountNumber;
    private double balance;
    private int customerRanking;
    private String internalInfo;

    public BankCustomer() {
    }

    public BankCustomer(Long id, String firstname, String lastname, String accountNumber, double balance, int customerRanking, String internalInfo) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customerRanking = customerRanking;
        this.internalInfo = internalInfo;
    }

    public BankCustomer(String firstname, String lastname, String accountNumber, double balance, int customerRanking, String internalInfo) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customerRanking = customerRanking;
        this.internalInfo = internalInfo;
    }
    
    public BankCustomer(Long id, String firstname, String lastname, String accountNumber, double balance) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public BankCustomer(String firstname, String lastname, String accountNumber, double balance) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getCustomerRanking() {
        return customerRanking;
    }

    public void setCustomerRanking(int customerRanking) {
        this.customerRanking = customerRanking;
    }

    public String getInternalInfo() {
        return internalInfo;
    }

    public void setInternalInfo(String internalInfo) {
        this.internalInfo = internalInfo;
    }

    @Override
    public String toString() {
        return "BankCustomer{" + "id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", accountNumber=" + accountNumber + ", balance=" + balance + ", customerRanking=" + customerRanking + ", internalInfo=" + internalInfo + '}';
    }
    
    

}
