package facades;

import dtos.BankCustomerDTO;
import entities.BankCustomer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class BankCustomerFacade {

    private static BankCustomerFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private BankCustomerFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static BankCustomerFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BankCustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    /**
     * Add a customer from a NON customerDTO (unsecure)
     * @param bankCustomer
     * @return 
     */
    public BankCustomer addCustomer(BankCustomer bankCustomer){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(bankCustomer);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return bankCustomer;
    }
    
    /**
     * Create a Customer from a customerDTO
     * @param bankCustomerDTO
     * @return 
     */
    public BankCustomerDTO createFromDTO(BankCustomerDTO bankCustomerDTO){
        String fullName = bankCustomerDTO.getFullName();
        String[] splitFullName = fullName.trim().split("\\s+");
        
        BankCustomer bankCustomer = new BankCustomer(splitFullName[0], null, bankCustomerDTO.getAccountNumberDTO(), bankCustomerDTO.getBalanceDTO());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(bankCustomer);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new BankCustomerDTO(bankCustomer);
    }
    
    /**
     * Get customerDTO by ID
     * @param id
     * @return 
     */
    public BankCustomerDTO getBankCustomerById(long id){
        EntityManager em = emf.createEntityManager();
        return new BankCustomerDTO(em.find(BankCustomer.class, id));
    }
    
    /**
     * Get list of customerDTO by name
     * @param name
     * @return 
     */
     public List<BankCustomerDTO> getBankCustomersDTOByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<BankCustomer> query 
                    = em.createQuery("Select bankCustomer FROM BankCustomer bankCustomer WHERE bankCustomer.firstname = :name", BankCustomer.class).setParameter("name", name);
            List<BankCustomer> bankCustomers = query.getResultList();
            return BankCustomerDTO.getAllBankCustomerDTOs(bankCustomers);
        } finally {
            em.close();
        }
    }
    
     /**
      * Get total number of customers
      * @return 
      */
    public long getNumberOfCustomers(){
        EntityManager em = emf.createEntityManager();
        try{
            long numberOfCustomers = (long)em.createQuery("SELECT COUNT(bankCustomer) FROM BankCustomer bankCustomer").getSingleResult();
            return numberOfCustomers;
        }finally{  
            em.close();
        }   
    }
    
    /**
     * Get all customers coverted to DTO
     * @return 
     */
    public List<BankCustomerDTO> getAllBankCustomersDTO(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<BankCustomer> query = em.createQuery("SELECT bankCustomer FROM BankCustomer bankCustomer", BankCustomer.class);
        List<BankCustomer> allBankCustomers = query.getResultList();
        return BankCustomerDTO.getAllBankCustomerDTOs(allBankCustomers);
    }
    
    /**
     * Get all customers NOT coverted to DTO (unsecure)
     * @return 
     */
    public List<BankCustomer> getAllBankCustomers(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<BankCustomer> query = em.createQuery("SELECT bankCustomer FROM BankCustomer bankCustomer", BankCustomer.class);
        List<BankCustomer> allBankCustomers = query.getResultList();
        return allBankCustomers;
    }
    
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        BankCustomerFacade fe = getFacadeExample(emf);
        fe.getAllBankCustomersDTO().forEach(BankCustomerDTO->System.out.println(BankCustomerDTO.toString()));
    }

}
