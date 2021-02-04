/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Employee;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tweny
 */
public class EmployeeFacadeTest {
    private static final EntityManagerFactory ENF = Persistence.createEntityManagerFactory("pu");
    private static final EmployeeFacade FE = EmployeeFacade.getEmployeeFacade(ENF);
    
    public EmployeeFacadeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        //        Add code to setup entities for test before running any test methods
//        FE.createEmployee("Arik", "Fantasivej", new BigDecimal(334455));
//        FE.createEmployee("Dina", "Hullavej", new BigDecimal(3355));
//        FE.createEmployee("Bente", "Mestervej", new BigDecimal(33554455));
    }
    
    @AfterClass
    public static void tearDownClass() {
        //        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }
    
    @Before
    public void setUp() {
        //        Put the test database in a proper state before each test is run
    }
    
    @After
    public void tearDown() {
        //        Remove any data after each test was run
    }

    /**
     * Test of getById method, of class EmployeeFacade.
     */
    @Test
    public void testGetById() {
//        FE.createEmployee("John", "Lotusvej", new BigDecimal(3366455));
//        Employee employee = FE.getEmployeeById(4);
//        assertEquals("John", employee.getName());
    }

    /**
     * Test of getEmployeeByName method, of class EmployeeFacade.
     */
    @Test
    public void testGetEmployeeByName() {
//        Employee employee = FE.getEmployeeByName("Arik");
//        assertEquals("Arik", employee.getName());
    }

    /**
     * Test of getAllEmployees method, of class EmployeeFacade.
     */
    @Test
    public void testGetAllEmployees() {
//        List<Employee> allEmployees = FE.getAllEmployees();
//        assertTrue(allEmployees.size() >= 4);
    }

    /**
     * Test of getEmployeesWithHighestSalary method, of class EmployeeFacade.
     */
    @Test
    public void testGetEmployeesWithHighestSalary() {
//        Employee employee = FE.getEmployeesWithHighestSalary();
//        assertEquals("Bente", employee.getName());
    }

    /**
     * Test of createEmployee method, of class EmployeeFacade.
     */
    @Test
    public void testCreateEmployee() {
//        FE.createEmployee("Jimmy", "Sandvej", new BigDecimal(331255));
//        List<Employee> allEmployees = FE.getAllEmployees();
//        assertEquals(5, allEmployees.size());
    }
    
}
