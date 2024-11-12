package src.models;

import java.util.List;

public class Tierarzt extends Person {
    private String spezialisierung;
    private List<Tierheim> tierheime;

    public Tierarzt(int id, String name, String kontaktDetails, String spezialisierung, List<Tierheim> tierheime) {
        super(id, name, kontaktDetails);
        this.spezialisierung = spezialisierung;
        this.tierheime = tierheime;
    }

    public String getSpezialisierung() {
        return spezialisierung;
    }

    public void setSpezialisierung(String spezialisierung) {
        this.spezialisierung = spezialisierung;
    }

    public List<Tierheim> getTierheime() {
        return tierheime;
    }

    public void setTierheime(List<Tierheim> tierheime) {
        this.tierheime = tierheime;
    }
}
