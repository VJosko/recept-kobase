package com.vudrag.kobaserecept.classes;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "info_table")
public class ReceptInfo {
    @PrimaryKey(autoGenerate = true)
    Long id;
    String ime;
    String datumIzrade;
    String datumIzmjene;
    String komentar;

    public ReceptInfo(String ime, String datumIzrade, String datumIzmjene, String komentar) {
        this.ime = ime;
        this.datumIzrade = datumIzrade;
        this.datumIzmjene = datumIzmjene;
        this.komentar = komentar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
