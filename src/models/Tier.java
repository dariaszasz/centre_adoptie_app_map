package models;

import java.io.Serializable;

public class Tier implements Serializable {
    private int id;
    private String name;
    private Tierart tierart;
    private int alter;
    private Gesundheitsakte gesundheitsakte;
    private Pflegeprogramm pflegeprogramm;
    private String status;

    // Constructor, getter, setter

    public Tier(int id, String name, Tierart tierart, int alter, Gesundheitsakte gesundheitsakte, Pflegeprogramm pflegeprogramm, String status) {
        this.id = id;
        this.name = name;
        this.tierart = tierart;
        this.alter = alter;
        this.gesundheitsakte = gesundheitsakte;
        this.pflegeprogramm = pflegeprogramm;
        this.status = status;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tierart getTierart() {
        return tierart;
    }

    public void setTierart(Tierart tierart) {
        this.tierart = tierart;
    }

    public int getAlter() {
        return alter;
    }

    public void setAlter(int alter) {
        this.alter = alter;
    }

    public Gesundheitsakte getGesundheitsakte() {
        return gesundheitsakte;
    }

    public void setGesundheitsakte(Gesundheitsakte gesundheitsakte) {
        this.gesundheitsakte = gesundheitsakte;
    }

    public Pflegeprogramm getPflegeprogramm() {
        return pflegeprogramm;
    }

    public void setPflegeprogramm(Pflegeprogramm pflegeprogramm) {
        this.pflegeprogramm = pflegeprogramm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
/*
    @Override
    public String toString() {
        return "ID: " + id + ", Nume: " + name + ", Tierart: " + tierart.getArtName() +
                ", Vârsta: " + alter + ", Status: " + status;
    }

 */
@Override
public String toString() {
    return "ID: " + id +
            ", Name: " + name +
            ", Tierart: " + tierart +
            ", Age: " + alter +
            ", Status: " + status +
            ", Health Record: " + (gesundheitsakte != null ? gesundheitsakte.toString() : "No record") +
            ", Care Program: " + (pflegeprogramm != null ? pflegeprogramm.toString() : "No program");
}


}
