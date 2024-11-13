package models;

public class Tierart {
    private int id;
    private String artName;
    private String besondereEigenschaften;

    public Tierart(int id, String artName, String besondereEigenschaften) {
        this.id = id;
        this.artName = artName;
        this.besondereEigenschaften = besondereEigenschaften;
    }

    // Getter and Setter
    public int getId() {
        return id;
    }

    public String getArtName() {
        return artName;
    }

    public String getBesondereEigenschaften() {
        return besondereEigenschaften;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setArtName(String artName) {
        this.artName = artName;
    }

    public void setBesondereEigenschaften(String besondereEigenschaften) {
        this.besondereEigenschaften = besondereEigenschaften;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + artName + ", Description: " + besondereEigenschaften;
    }

}
