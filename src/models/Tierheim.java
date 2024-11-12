package models;

import java.util.List;

public class Tierheim {
    private int id;
    private String name;
    private String adresse;
    private List<Tier> tiere;
    private List<Freiwilliger> freiwillige;
    private List<Tierarzt> tierärzte;

    public Tierheim(int id, String name, String adresse, List<Tier> tiere, List<Freiwilliger> freiwillige, List<Tierarzt> tierärzte) {
        this.id = id;
        this.name = name;
        this.adresse = adresse;
        this.tiere = tiere;
        this.freiwillige = freiwillige;
        this.tierärzte = tierärzte;
    }

    // Getter and Setter
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAdresse() {
        return adresse;
    }

    public List<Tier> getTiere() {
        return tiere;
    }

    public List<Freiwilliger> getFreiwillige() {
        return freiwillige;
    }

    public List<Tierarzt> getTierärzte() {
        return tierärzte;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setTiere(List<Tier> tiere) {
        this.tiere = tiere;
    }

    public void setFreiwillige(List<Freiwilliger> freiwillige) {
        this.freiwillige = freiwillige;
    }

    public void setTierärzte(List<Tierarzt> tierärzte) {
        this.tierärzte = tierärzte;
    }
}
