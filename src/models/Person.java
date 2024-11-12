package models;

public abstract class Person {
    private int id;
    private String name;
    private String kontaktDetails;

    public Person(int id, String name, String kontaktDetails) {
        this.id = id;
        this.name = name;
        this.kontaktDetails = kontaktDetails;
    }

    // Getter und Setter
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getKontaktDetails() {
        return kontaktDetails;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKontaktDetails(String kontaktDetails) {
        this.kontaktDetails = kontaktDetails;
    }
}
