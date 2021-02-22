package com.vudrag.kobaserecept.recept;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.vudrag.kobaserecept.ReceptInfo;
import com.vudrag.kobaserecept.Sastojak;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReceptViewModel extends ViewModel {

    MutableLiveData<ArrayList<Sastojak>> sastojci = new MutableLiveData<>();
    public String ime = "";

    public ReceptViewModel() {
        ArrayList<Sastojak> s = new ArrayList<>();
        s.add(new Sastojak("", "0",0));
        sastojci.setValue(s);
    }

    public void onAddSastojak(){
        Sastojak sastojak = new Sastojak("", "0",0);
        sastojci.getValue().add(sastojak);
        sastojci.setValue(sastojci.getValue());
    }

    public void onSpremiRecept(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        ReceptInfo receptInfo = new ReceptInfo(0,ime,dateFormat.format(date),dateFormat.format(date),"");
        //TODO: spremiti
    }
}
