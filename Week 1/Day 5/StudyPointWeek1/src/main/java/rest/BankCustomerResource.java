package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.BankCustomerDTO;
import entities.BankCustomer;
import utils.EMF_Creator;
import facades.BankCustomerFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("bankcustomer")
public class BankCustomerResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final BankCustomerFacade FACADE =  BankCustomerFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllBankCustomers() {
        List<BankCustomer> allBankCustomers = FACADE.getAllBankCustomers();
        String JSONString = GSON.toJson(allBankCustomers);
        return JSONString;
    }
    
    @Path("dto")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllBankCustomersDTO() {
        List<BankCustomerDTO> allBankCustomersDTOs = FACADE.getAllBankCustomersDTO();
        return new Gson().toJson(allBankCustomersDTOs);
    }
    
    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getBankCustomersById(@PathParam("id") long id) {
        BankCustomerDTO bankCustomerDTO = FACADE.getBankCustomerById(id);
        return new Gson().toJson(bankCustomerDTO);
    }
//    @Path("count")
//    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    public String getRenameMeCount() {
//        long count = FACADE.getRenameMeCount();
//        //System.out.println("--------------->"+count);
//        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
//    }
}
