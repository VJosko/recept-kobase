package com.vudrag.kobaserecept.kalkulator;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.vudrag.kobaserecept.Recept;
import com.vudrag.kobaserecept.Repository;
import com.vudrag.kobaserecept.Sastojak;

import java.util.ArrayList;

public class KalkulatorViewModel extends ViewModel {

    int receptId;
    public String meso = "0";
    private ArrayList<Recept> recepti;
    public MutableLiveData<ArrayList<Sastojak>> racunica = new MutableLiveData<>();

    public KalkulatorViewModel(int receptId) {
        this.receptId = receptId;
        Repository repository = Repository.getInstance();
        recepti = repository.getRecepte();
        racunanje();
    }

    public void racunanje(){
        ArrayList<Sastojak> x = new ArrayList<>();
        for (Sastojak sastojak: recepti.get(receptId).getSastojci()) {
            double rac = sastojak.getDoubleOmjer() * Double.parseDouble(meso);
            Sastojak s = new Sastojak(sastojak.getIme(), Double.toString(rac) ,0);
            x.add(s);
        }
        racunica.setValue(x);
    }
}
