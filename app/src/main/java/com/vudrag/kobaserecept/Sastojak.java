package com.vudrag.kobaserecept;

public class Sastojak{

    int id;
    String ime;
    String omjer;
    int receptId;

    public Sastojak(String ime,String omjer, int receptId) {
        this.ime = ime;
        this.omjer = omjer;
        this.receptId = receptId;
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
