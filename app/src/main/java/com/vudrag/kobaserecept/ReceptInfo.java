package com.vudrag.kobaserecept;

public class ReceptInfo {

    int id;
    String ime;
    String datumIzrade;
    String datumIzmjene;
    String komentar;

    public ReceptInfo(int id, String ime, String datumIzrade, String datumIzmjene, String komentar) {
        this.id = id;
        this.ime = ime;
        this.datumIzrade = datumIzrade;
        this.datumIzmjene = datumIzmjene;
        this.komentar = komentar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getDatumIzrade() {
        return datumIzrade;
    }

    public void setDatumIzrade(String datumIzrade) {
        this.datumIzrade = datumIzrade;
    }

    public String getDatumIzmjene() {
        return datumIzmjene;
    }

    public void setDatumIzmjene(String datumIzmjene) {
        this.datumIzmjene = datumIzmjene;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }
}
