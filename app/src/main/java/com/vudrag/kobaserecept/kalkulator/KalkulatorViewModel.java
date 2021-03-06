package com.vudrag.kobaserecept.kalkulator;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.vudrag.kobaserecept.classes.Recept;
import com.vudrag.kobaserecept.Repository;
import com.vudrag.kobaserecept.classes.Sastojak;

import java.util.ArrayList;

public class KalkulatorViewModel extends ViewModel {

    private Context context;
    int receptId;
    public String meso = "0";
    private ArrayList<Recept> recepti;
    public MutableLiveData<ArrayList<Sastojak>> racunica = new MutableLiveData<>();
    Repository repository;

    public KalkulatorViewModel(int receptId) {
        this.receptId = receptId;
        repository = Repository.getInstance();
        recepti = repository.getRecepte();
        racunanje();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void racunanje(){
        ArrayList<Sastojak> x = new ArrayList<>();
        for (Sastojak sastojak: recepti.get(receptId).getSastojci()) {
            double rac = sastojak.getDoubleOmjer() * Double.parseDouble(meso);
            Sastojak s = new Sastojak(sastojak.getIme(), Double.toString(rac));
            x.add(s);
        }
        racunica.setValue(x);
    }
}
