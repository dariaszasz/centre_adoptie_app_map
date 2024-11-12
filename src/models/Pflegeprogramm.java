package models;

public class Pflegeprogramm {
    private int id;
    private String futterplan;
    private String medizinischeVersorgung;

    public Pflegeprogramm(int id, String futterplan, String medizinischeVersorgung) {
        this.id = id;
        this.futterplan = futterplan;
        this.medizinischeVersorgung = medizinischeVersorgung;
    }

    // Getter und Setter
    public int getId() {
        return id;
    }

    public String getFutterplan() {
        return futterplan;
    }

    public String getMedizinischeVersorgung() {
        return medizinischeVersorgung;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFutterplan(String futterplan) {
        this.futterplan = futterplan;
    }

    public void setMedizinischeVersorgung(String medizinischeVersorgung) {
        this.medizinischeVersorgung = medizinischeVersorgung;
    }
}
