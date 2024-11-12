package src.models;

import java.util.List;

public class Adoptant extends Person {
    private List<Adoptionsantrag> adoptionsAntraege;

    public Adoptant(int id, String name, String kontaktDetails, List<Adoptionsantrag> adoptionsAntraege) {
        super(id, name, kontaktDetails);
        this.adoptionsAntraege = adoptionsAntraege;
    }

    public List<Adoptionsantrag> getAdoptionsAntraege() {
        return adoptionsAntraege;
    }
