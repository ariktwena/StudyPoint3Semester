package facades;

import entities.Employee;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class EmployeeFacade {
    
    private static EmployeeFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private EmployeeFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Get employee by id
     * @param id
     * @return 
     */
    public Employee getEmployeeById(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Employee employee = em.find(Employee.class, id);
            return employee;
        } finally {
            em.close();
        }
    }
    
    /**
     * Get employee by name
     * @param name
     * @return 
     */
    public Employee getEmployeeByName(String name) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("Select employee FROM Employee employee WHERE employee.name = :name").setParameter("name", name);
            Employee employee = (Employee) query.getSingleResult();
            return employee;
        } finally {
            em.close();
        }
    }
    
    /**
     * Get all employees
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
    
    public Employee getEmployeesWithHighestSalary() {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("select e from Employee e where e.salary = (Select MAX(e2.salary) FROM Employee e2)");
            Employee employee = (Employee) query.getSingleResult();
            return employee;
        } finally {
            em.close();
        }
    }
    
    /**
     * Create employee
     * @param name
     * @param address
     * @param salary
     * @return 
     */
    public Employee createEmployee(String name, String address, BigDecimal salary) {
        EntityManager em = getEntityManager();
        Employee employee = new Employee(name, address, salary);
        try {
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
            return employee;
        } finally {
            em.close();
        }    
    }
    
    public void init(){
        EntityManager em = getEntityManager();
        Employee e1 = new Employee("Arik", "Fantasivej", new BigDecimal(334455));
        Employee e2 = new Employee("Dina", "Hullavej", new BigDecimal(3355));
        Employee e3 = new Employee("Bente", "Mestervej", new BigDecimal(33554455));
        Employee e4 = new Employee("John", "Lotusvej", new BigDecimal(3366455));
        Employee e5 = new Employee("Jimmy", "Sandvej", new BigDecimal(331255));
        try {
            em.getTransaction().begin();
            em.persist(e1);
            em.persist(e2);
            em.persist(e3);
            em.persist(e4);
            em.persist(e5);
            em.getTransaction().commit();
        } finally {
            em.close();
        } 
    }
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EmployeeFacade facade = EmployeeFacade.getEmployeeFacade(emf);
        
        facade.createEmployee("Arik", "Fantasivej", new BigDecimal(334455));
    }
    
}
