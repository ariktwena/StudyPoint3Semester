/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.BankCustomerDTO;
import entities.BankCustomer;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        BankCustomerFacade fe = BankCustomerFacade.getFacadeExample(emf);
        fe.createFromDTO(new BankCustomerDTO(new BankCustomer("John", "Smith", "22cff54", 23445, 1, "Good customer")));
        fe.createFromDTO(new BankCustomerDTO(new BankCustomer("Eddie", "Johnsson", "345vvg45", 445562, 3, "Bad customer")));
        fe.createFromDTO(new BankCustomerDTO(new BankCustomer("Frank", "Clinton", "34yvg45", 89962, 2, "Avg. customer")));   
    }
    
    public static void main(String[] args) {
        populate();
    }
}
