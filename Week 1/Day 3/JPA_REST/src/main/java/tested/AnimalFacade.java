/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tested;

import entity.Animal;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Tweny
 */
public class AnimalFacade {
    
    private EntityManagerFactory emf;

    AnimalFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    private EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    /**
     * Add animal to DB
     * @param type
     * @param sound
     * @return 
     */
    public Animal addAnimal(Animal animal) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(animal);
            em.getTransaction().commit();
            return animal;
        } finally {
            em.close();
        }
    }
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        AnimalFacade facade = new AnimalFacade(Persistence.createEntityManagerFactory("pu"));
        
        //Add Animal to DB
        Animal animal1 = new Animal("Dog", "Vuf");
        Animal animal2 = new Animal("Duck", "Quack");
        Animal animal3 = new Animal("Cat", "Miav");
        
        facade.addAnimal(animal1);
        facade.addAnimal(animal2);
        facade.addAnimal(animal3);
        
        
    }
}
