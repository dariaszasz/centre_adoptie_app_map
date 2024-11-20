package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a Shelter, which contains lists of animals, volunteers, and veterinarians.
 * It allows managing information about the shelter and its resources.
 * This class implements Serializable to allow instances to be saved or transmitted.
 */
public class Shelter implements Serializable {
    private int id;
    private String name;
    private String address;
    private List<Animal> animals;
    private List<Volunteer> volunteers;
    private List<Veterinarian> veterinarians;

    /**
     * Constructor for creating a Shelter instance with specified ID, name, and address.
     * Initializes empty lists for animals, volunteers, and veterinarians.
     *
     * @param id the ID of the shelter
     * @param name the name of the shelter
     * @param address the address of the shelter
     */
    public Shelter(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.animals = new ArrayList<>();
        this.volunteers = new ArrayList<>();
        this.veterinarians = new ArrayList<>();
    }

    /**
     * Returns the ID of the shelter.
     *
     * @return the ID of the shelter
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name of the shelter.
     *
     * @return the name of the shelter
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the address of the shelter.
     *
     * @return the address of the shelter
     */
    public String getAddress() {
        return address;
    }

    /**
     * Returns the list of animals in the shelter.
     *
     * @return a list of animals
     */
    public List<Animal> getAnimals() {
        return animals;
    }

    /**
     * Adds an animal to the shelter.
     *
     * @param animal the animal to add to the shelter
     */
    public void addAnimal(Animal animal) {
        this.animals.add(animal);
    }

    /**
     * Returns the list of volunteers working at the shelter.
     *
     * @return a list of volunteers
     */
    public List<Volunteer> getVolunteers() {
        return volunteers;
    }

    /**
     * Adds a volunteer to the shelter.
     *
     * @param volunteer the volunteer to add to the shelter
     */
    public void addVolunteer(Volunteer volunteer) {
        this.volunteers.add(volunteer);
    }

    /**
     * Returns the list of veterinarians associated with the shelter.
     *
     * @return a list of veterinarians
     */
    public List<Veterinarian> getVeterinarians() {
        return veterinarians;
    }

    /**
     * Adds a veterinarian to the shelter.
     *
     * @param veterinarian the veterinarian to add to the shelter
     */
    public void addVeterinarian(Veterinarian veterinarian) {
        this.veterinarians.add(veterinarian);
    }

    /**
     * Returns a string representation of the shelter, including its ID, name, address,
     * and the number of animals it contains.
     *
     * @return a string representation of the shelter
     */
    @Override
    public String toString() {
        return "Shelter ID: " + id + ", Name: " + name + ", Address: " + address + ", Animals: " + animals.size();
    }
}
