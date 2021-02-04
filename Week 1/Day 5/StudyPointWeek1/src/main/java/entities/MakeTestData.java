/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dtos.BankCustomerDTO;
import facades.BankCustomerFacade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import utils.EMF_Creator;

/**
 *
 * @author Tweny
 */
public class MakeTestData {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        BankCustomer bankCustomer1 = new BankCustomer("John", "Smith", "22cff54", 23445, 1, "Good customer");
        BankCustomer bankCustomer2 = new BankCustomer("Eddie", "Johnsson", "345vvg45", 445562, 3, "Bad customer");
        BankCustomer bankCustomer3 = new BankCustomer("Frank", "Clinton", "34yvg45", 89962, 2, "Avg. customer"); 
        
        try {
            em.getTransaction().begin();
            em.persist(bankCustomer1);
            em.persist(bankCustomer2);
            em.persist(bankCustomer3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }   
    }
}
