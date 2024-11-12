package models;

import src.models.Adoptant;

import java.util.Date;

public class Adoptionsantrag {
    private int id;
    private Adoptant adoptant;
    private Tier tier;
    private Date datumAntrag;
    private String status; // z.B. genehmigt, abgelehnt, ausstehend

    public Adoptionsantrag(int id, Adoptant adoptant, Tier tier, Date datumAntrag, String status) {
        this.id = id;
        this.adoptant = adoptant;
        this.tier = tier;
        this.datumAntrag = datumAntrag;
        this.status = status;
    }

    // Getter und Setter
    public int getId() {
        return id;
    }

    public Adoptant getAdoptant() {
        return adoptant;
    }

    public Tier getTier() {
        return tier;
    }

    public Date getDatumAntrag() {
        return datumAntrag;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAdoptant(Adoptant adoptant) {
        this.adoptant = adoptant;
    }

    public void setTier(Tier tier) {
        this.tier = tier;
    }

    public void setDatumAntrag(Date datumAntrag) {
        this.datumAntrag = datumAntrag;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
