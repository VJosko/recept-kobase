package com.vudrag.kobaserecept.classes;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.ArrayList;
import java.util.List;

public class Recept {
    @Embedded
    ReceptInfo receptInfo;
    @Relation(parentColumn = "id", entityColumn = "id")
    List<Sastojak> sastojci;

    public Recept(ReceptInfo receptInfo, List<Sastojak> sastojci) {
        this.receptInfo = receptInfo;
        this.sastojci = sastojci;
    }

    public ReceptInfo getReceptInfo() {
        return receptInfo;
    }

    public void setReceptInfo(ReceptInfo receptInfo) {
        this.receptInfo = receptInfo;
    }

    public List<Sastojak> getSastojci() {
        return sastojci;
    }

    public void setSastojci(ArrayList<Sastojak> sastojci) {
        this.sastojci = sastojci;
    }
}
