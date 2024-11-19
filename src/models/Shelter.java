package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Shelter implements Serializable {
    private int id;
    private String name;
    private String address;
    private List<Animal> animals;
    private List<Volunteer> volunteers;
    private List<Veterinarian> veterinarians;

    public Shelter(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.animals = new ArrayList<>();
        this.volunteers = new ArrayList<>();
        this.veterinarians = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void addAnimal(Animal animal) {
        this.animals.add(animal);
    }

    public List<Volunteer> getVolunteers() {
        return volunteers;
    }

    public void addVolunteer(Volunteer volunteer) {
        this.volunteers.add(volunteer);
    }

    public List<Veterinarian> getVeterinarians() {
        return veterinarians;
    }

    public void addVeterinarian(Veterinarian veterinarian) {
        this.veterinarians.add(veterinarian);
    }

    @Override
    public String toString() {
        return "Shelter ID: " + id + ", Name: " + name + ", Address: " + address + ", Animals: " + animals.size();
    }
}