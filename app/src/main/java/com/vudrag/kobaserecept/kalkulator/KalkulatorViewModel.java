package com.vudrag.kobaserecept.kalkulator;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.vudrag.kobaserecept.classes.Recept;
import com.vudrag.kobaserecept.Repository;
import com.vudrag.kobaserecept.classes.Sastojak;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class KalkulatorViewModel extends ViewModel {

    private Context context;
    int receptId;
    public MutableLiveData<String> meso = new MutableLiveData<>();
    public String ime;
    public String notes = "";
    private ArrayList<Recept> recepti;
    public MutableLiveData<ArrayList<Sastojak>> racunica = new MutableLiveData<>();
    Repository repository;

    public KalkulatorViewModel(int receptId) {
        this.receptId = receptId;
        meso.setValue("");
        repository = Repository.getInstance();
        recepti = repository.getRecepte();
        ime = recepti.get(receptId).getReceptInfo().getIme();
        notes = recepti.get(receptId).getReceptInfo().getKomentar();
        racunanje();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void racunanje() {
        DecimalFormat format = new DecimalFormat("0.##");
        ArrayList<Sastojak> x = new ArrayList<>();
        for (Sastojak sastojak : recepti.get(receptId).getSastojci()) {
            double rac = 0;
            if (!meso.getValue().equals(""))
                rac = sastojak.getDoubleOmjer() * Double.parseDouble(meso.getValue());
            Sastojak s = new Sastojak(sastojak.getIme(), format.format(rac));
            x.add(s);
        }
        racunica.setValue(x);
    }

}
