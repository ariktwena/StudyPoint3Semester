/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Animal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Tweny
 */
@Path("animals_db")
public class AnimalFromDB {

    //EntityManagerFactory
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    //GSON bruges til at konverterer JSON
    private static Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AnimalFromDB
     */
    public AnimalFromDB() {
    }

    /**
     * Create an EntityManager
     *
     * @return
     */
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Get all animals http://localhost:8080/JPA_REST/api/animals_db/animals
     *
     * @return
     */
    @Path("animals")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimals() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Animal> query = em.createQuery("SELECT animal FROM Animal animal", Animal.class);
            List<Animal> animals = query.getResultList();
            return new Gson().toJson(animals);
        } finally {
            em.close();
        }
    }

    /**
     * Gey animal by ID
     * http://localhost:8080/JPA_REST/api/animals_db/animalbyid/2
     *
     * @param id
     * @return
     */
    @Path("animalbyid/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalById(@PathParam("id") int id) {

        EntityManager em = getEntityManager();
        System.out.println(id);
        try {
            Animal animal = em.find(Animal.class, id);
            System.out.println(animal);
            if (animal != null) {
                return new Gson().toJson(animal);
            } else {
                String error = "null";
                return new Gson().toJson(error);
            }
        } finally {
            em.close();
        }
    }

    /**
     * Get Animal by type
     * http://localhost:8080/JPA_REST/api/animals_db/animalbytype/Dog
     *
     * @param type
     * @return
     */
    @Path("animalbytype/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalByType(@PathParam("type") String type) {

        EntityManager em = getEntityManager();
        System.out.println(type);
        try {
            TypedQuery<Animal> query
                    = em.createQuery("SELECT animal FROM Animal animal WHERE animal.type = :type", Animal.class).setParameter("type", type);
            return new Gson().toJson(query.getResultList());
        } finally {
            em.close();
        }
    }

    @Path("random_animal")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandomAnimal() {

        EntityManager em = getEntityManager();
        try {
            TypedQuery<Animal> query = em.createQuery("SELECT animal FROM Animal animal", Animal.class);
            List<Animal> allAnimals = query.getResultList();
            
            if(allAnimals.size() > 0){
                //Generate random number from 0 -> allAnimals.size()
                Random rand = new Random();
                //Get random id from 1 -> last id
                int randomNumber = rand.nextInt(allAnimals.size()) + 1; 
                Animal animal = em.find(Animal.class, randomNumber);
                System.out.println(animal);
                return new Gson().toJson(animal);
               
            } else {
                String error = "null";
                return new Gson().toJson(error);
            }
        } finally {
            em.close();
        }
    }

}
