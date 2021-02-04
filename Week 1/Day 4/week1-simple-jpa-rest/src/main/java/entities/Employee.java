package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    private String name;
    private String address;
    private BigDecimal salary;

    public Employee(Long id, String name, String address, BigDecimal salary) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public Employee(String name, String address, BigDecimal salary) {
        this.name = name;
        this.address = address;
        this.salary = salary;
    }
    
    public Employee() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal Salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", name=" + name + ", address=" + address + ", Salary=" + salary + '}';
    }
    
    
    
}
