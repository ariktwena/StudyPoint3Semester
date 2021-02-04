package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Employee;
import facades.EmployeeFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("employee")
public class EmployeeResource {
    
    //NOTE: Change Persistence unit name according to your setup
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu"); 
    EmployeeFacade facade =  EmployeeFacade.getEmployeeFacade(emf);
    
    //GSON bruges til at konverterer JSON
//    static Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    
    @Path("init")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String init() {
        facade.init();
        String ok = "DB updated";
        return new Gson().toJson(ok);
//        String JSONString = GSON.toJson(ok);
//        return JSONString;
    }
    
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllEmployees() {
        List<Employee> allEmployees = facade.getAllEmployees();
        return new Gson().toJson(allEmployees);
//        String JSONString = GSON.toJson(allEmployees);
//        return JSONString;
    }
    
    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getEmployeeById(@PathParam("id") long id) {
        Employee employee = facade.getEmployeeById(id);
        return new Gson().toJson(employee);
//        String JSONString = GSON.toJson(employee);
//        return JSONString;
    }
    
    @Path("highestpaid")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getHighestPaidEmployee() {
        System.out.print("hallo");
        Employee employee = facade.getEmployeesWithHighestSalary();
        System.out.print(employee.getName());
        return new Gson().toJson(employee);
    }
    
    @Path("name/{name}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getEmployeeById(@PathParam("name") String name) {
        Employee employee = facade.getEmployeeByName(name);
        return new Gson().toJson(employee);
//        String JSONString = GSON.toJson(employee);
//        return JSONString;
    }

//    @POST
//    @Consumes({MediaType.APPLICATION_JSON})
//    public void create(Employee entity) {
//        throw new UnsupportedOperationException();
//    }
//    
//    @PUT
//    @Path("/{id}")
//    @Consumes({MediaType.APPLICATION_JSON})
//    public void update(Employee entity, @PathParam("id") int id) {
//        throw new UnsupportedOperationException();
//    }
}
