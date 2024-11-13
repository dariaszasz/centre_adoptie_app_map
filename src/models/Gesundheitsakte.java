package models;

import java.util.List;

public class Gesundheitsakte {
    private int id;
    private List<String> diagnosen;
    private List<String> behandlungen;
    private Tierarzt tierarzt;

    public Gesundheitsakte(int id, List<String> diagnosen, List<String> behandlungen, Tierarzt tierarzt) {
        this.id = id;
        this.diagnosen = diagnosen;
        this.behandlungen = behandlungen;
        this.tierarzt = tierarzt;
    }

    // Getter and Setter
    public int getId() {
        return id;
    }

    public List<String> getDiagnosen() {
        return diagnosen;
    }

    public List<String> getBehandlungen() {
        return behandlungen;
    }

    public Tierarzt getTierarzt() {
        return tierarzt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDiagnosen(List<String> diagnosen) {
        this.diagnosen = diagnosen;
    }

    public void setBehandlungen(List<String> behandlungen) {
        this.behandlungen = behandlungen;
    }

    public void setTierarzt(Tierarzt tierarzt) {
        this.tierarzt = tierarzt;
    }

    @Override
    public String toString() {
        return "Health History: " + diagnosen +
                ", Medications: " + behandlungen +
                ", Vet: " + (tierarzt != null ? tierarzt.toString() : "No vet assigned");
    }
}
