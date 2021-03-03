package com.vudrag.kobaserecept;

import java.util.ArrayList;

public class Recept {

    ReceptInfo receptInfo;
    ArrayList<Sastojak> sastojci;

    public Recept(ReceptInfo receptInfo, ArrayList<Sastojak> sastojci) {
        this.receptInfo = receptInfo;
        this.sastojci = sastojci;
    }

    public ReceptInfo getReceptInfo() {
        return receptInfo;
    }

    public void setReceptInfo(ReceptInfo receptInfo) {
        this.receptInfo = receptInfo;
    }

    public ArrayList<Sastojak> getSastojci() {
        return sastojci;
    }

    public void setSastojci(ArrayList<Sastojak> sastojci) {
        this.sastojci = sastojci;
    }
}
