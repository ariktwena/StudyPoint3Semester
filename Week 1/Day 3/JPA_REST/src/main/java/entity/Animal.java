/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Tweny
 */
@Entity
@Table(name = "animals")
public class Animal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column(name="type", length=50, nullable=false, unique=false)
    private String type;
    @Column(name="sound", length=50, nullable=false, unique=false)
    private String sound;

    public Animal(Integer id, String type, String sound) {
        this.id = id;
        this.type = type;
        this.sound = sound;
    }

    public Animal(String type, String sound) {
        this.type = type;
        this.sound = sound;
    }

    public Animal() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    @Override
    public String toString() {
        return "Animal{" + "id=" + id + ", type=" + type + ", sound=" + sound + '}';
    }

}
