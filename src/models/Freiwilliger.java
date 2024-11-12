package models;

import java.util.List;

public class Freiwilliger extends Person {
    private String erfahrung;
    private List<Tierheim> tierheime;

    public Freiwilliger(int id, String name, String kontaktDetails, String erfahrung, List<Tierheim> tierheime) {
        super(id, name, kontaktDetails);
        this.erfahrung = erfahrung;
        this.tierheime = tierheime;
    }

    public String getErfahrung() {
        return erfahrung;
    }

    public void setErfahrung(String erfahrung) {
        this.erfahrung = erfahrung;
    }

    public List<Tierheim> getTierheime() {
        return tierheime;
    }

    public void setTierheime(List<Tierheim> tierheime) {
        this.tierheime = tierheime;
    }
}
