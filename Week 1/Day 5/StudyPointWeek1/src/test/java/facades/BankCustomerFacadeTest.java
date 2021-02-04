package facades;

import utils.EMF_Creator;
import entities.BankCustomer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class BankCustomerFacadeTest {

    private static EntityManagerFactory emf;
    private static BankCustomerFacade facade;

    public BankCustomerFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = BankCustomerFacade.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("BankCustomer.deleteAllRows").executeUpdate();
            em.persist(new BankCustomer("Some txt", "More text", "344gg", 435453));
            em.persist(new BankCustomer("aaa", "bbb", "54hhg", 2123));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    @Test
    public void numberOfCustomers() {
        assertEquals(2, facade.getAllBankCustomers().size(), "Expects two rows in the database");
    }

    @Test
    public void numberOfCustomersDTO() {
        assertEquals(2, facade.getAllBankCustomersDTO().size(), "Expects two rows in the database");
    }

    @Test
    public void getCustomerByID() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("BankCustomer.deleteAllRows").executeUpdate();
            em.persist(new BankCustomer("Arik", "Twena", "64hyyg", 45653));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        assertEquals("64hyyg", facade.getBankCustomerById(3).getAccountNumberDTO(), "Expect 64hyyg account number");
        assertEquals("Arik Twena", facade.getBankCustomerById(3).getFullName(), "Expect Arik Twenar");
    }

    @Test
    public void getCustomerByName() {
        assertEquals(1, facade.getBankCustomersDTOByName("aaa").size(), "Expects one rows in the database");
    }

    @Test
    public void addCustomer() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("BankCustomer.deleteAllRows").executeUpdate();
            em.persist(new BankCustomer("Arik", "Twena", "64hyyg", 45653));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        assertEquals(1, facade.getAllBankCustomers().size(), "Expects one rows in the database becouse of drop-and-create");
    }

}
