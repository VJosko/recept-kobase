package com.vudrag.kobaserecept.classes;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/*, foreignKeys = {@ForeignKey(
        entity = ReceptInfo.class,
        childColumns = "id",
        parentColumns = "id",
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
)}*/

@Entity(tableName = "sastojak_table")
public class Sastojak{

    @PrimaryKey(autoGenerate = true)
    Long sastojakId;
    String ime;
    String omjer;
    Long id;

    public Sastojak(String ime,String omjer) {
        this.ime = ime;
        this.omjer = omjer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long receptId) {
        this.id = receptId;
    }

    public Long getSastojakId() {
        return sastojakId;
    }

    public void setSastojakId(Long sastojakId) {
        this.sastojakId = sastojakId;
    }

    public String getIme() {
        return ime;
    }

    public String getOmjer() {
        return omjer;
    }

    public double getDoubleOmjer(){return Double.parseDouble(omjer);}

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setOmjer(String omjer) {
        if(!omjer.equals("")) this.omjer = omjer;
    }
}
