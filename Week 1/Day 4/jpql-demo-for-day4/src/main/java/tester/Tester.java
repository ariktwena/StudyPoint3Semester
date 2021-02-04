package tester;

import entity.Employee;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class Tester {

    private static EntityManagerFactory emf;
    private static Tester instance;

    private Tester() {
    }

    /**
     * Singleton that make an instance
     *
     * @param _emf
     * @return
     */
    public static Tester getBookFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new Tester();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Get Employee with salery over 10000
     * @return 
     */
    public List<Employee> findEmployeeWithHighSalery() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Employee> query
                    = em.createQuery("SELECT employee FROM Employee employee WHERE employee.salary > 100000", Employee.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Get employee by id
     * @param id
     * @return 
     */ 
    public Employee findById(String id) {
        EntityManager em = emf.createEntityManager();
        try {
            Employee employee = em.find(Employee.class, id);
            return employee;
        } finally {
            em.close();
        }
    }
    
    /**
     * Find the highest salery
     * @return 
     */
    public BigDecimal findHighestSalary() {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("Select MAX(employee.salary) FROM Employee employee");
            BigDecimal salary = (BigDecimal) query.getSingleResult();
            return salary;
        } finally {
            em.close();
        }
    }
    
    /**
     * Get all first names
     * @return 
     */
    public List<String> allFirstNames() {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT employee.firstName FROM Employee employee ORDER BY employee.salary DESC");
            List<String> allFirstNames = (List<String>) query.getResultList();
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    /**
     * Get number of employee
     * @return 
     */
    public List<Employee> getAllEmployees() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Employee> query
                     = em.createQuery("SELECT employee FROM Employee employee", Employee.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    public long getAllEmployees1() {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT COUNT(e) FROM Employee e");
            Long employeeCount = (long) query.getSingleResult();
            return employeeCount;
        } finally {
            em.close();
        }
    }

    /**
     * Find employee with highest salary
     * @return 
     */
    public List<Employee> findEmployeeWithHighestSalery() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Employee> query
                     = em.createQuery("SELECT employee FROM Employee employee ORDER BY employee.salary DESC", Employee.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    public Employee findEmployeeWithHighestSalery1() {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("select e from Employee e where e.salary = (Select MAX(e2.salary) FROM Employee e2)");
            Employee employee = (Employee) query.getSingleResult();
            return employee;
        } finally {
            em.close();
        }
    }
  
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        //Facade to the methods
        Tester facade = Tester.getBookFacade(emf);
        
        try {
            em.getTransaction().begin();
            em.persist(new Employee("xa12tt", "Kurt", "Wonnegut", new BigDecimal(335567)));
            em.persist(new Employee("hyu654", "Hanne", "Olsen", new BigDecimal(435867)));
            em.persist(new Employee("uio876", "Jan", "Olsen", new BigDecimal(411567)));
            em.persist(new Employee("klo999", "Irene", "Petersen", new BigDecimal(33567)));
            em.persist(new Employee("jik666", "Tian", "Wonnegut", new BigDecimal(56567)));
            em.getTransaction().commit();

            //Complete all these small tasks. Your will find the solution to all, but the last,
            //In this document: https://en.wikibooks.org/wiki/Java_Persistence/JPQL#JPQL_supported_functions
            
            //1) Create a query to fetch all employees with a salary > 100000 and print out all the salaries
            List<Employee> employees = facade.findEmployeeWithHighSalery();
            for(Employee employee : employees){
                System.out.println(employee.toString());
            }
            
            //2) Create a query to fetch the employee with the id "klo999" and print out the firstname
            Employee employee = facade.findById("klo999");
            System.out.println(employee.toString());
            
            //3) Create a query to fetch the highest salary and print the value
            BigDecimal salary = facade.findHighestSalary();
            System.out.println(salary);
            
            //4) Create a query to fetch the firstName of all Employees and print the names
            List<String> allNames = facade.allFirstNames();
            for(String string : allNames){
                System.out.println(string);
            }
            
            //5 Create a query to calculate the number of employees and print the number
            System.out.println("Number of employees " + facade.getAllEmployees().size());
            
            System.out.println("Number of employees " + facade.getAllEmployees1());
            
            
            //6 Create a query to fetch the Employee with the higest salary and print all his details
            List<Employee> employeesSalery = facade.findEmployeeWithHighestSalery();
            System.out.println(employeesSalery.get(0).toString() + " " + employeesSalery.get(0).getSalary());
            
            Employee employeesSalery1 = facade.findEmployeeWithHighestSalery1();
            System.out.println(employeesSalery1.toString() + " salary: " + employeesSalery1.getSalary());
                  
            
        } finally {
            em.close();
            emf.close();
        }
    }

}
