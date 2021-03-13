package com.vudrag.kobaserecept.recept;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.vudrag.kobaserecept.classes.Recept;
import com.vudrag.kobaserecept.classes.ReceptInfo;
import com.vudrag.kobaserecept.Repository;
import com.vudrag.kobaserecept.classes.Sastojak;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReceptViewModel extends ViewModel {

    private Context context;
    MutableLiveData<ArrayList<Sastojak>> sastojci = new MutableLiveData<>();
    public String ime = "";
    Repository repository;

    public ReceptViewModel(int position) {
        repository = Repository.getInstance();
        ArrayList<Sastojak> s = new ArrayList<>();
        s.add(new Sastojak("", "0"));
        sastojci.setValue(s);
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void onAddSastojak(){
        Sastojak sastojak = new Sastojak("", "0");
        sastojci.getValue().add(sastojak);
        sastojci.setValue(sastojci.getValue());
    }

    public void onSpremiRecept(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        ReceptInfo receptInfo = new ReceptInfo(ime,dateFormat.format(date),dateFormat.format(date),"");
        Recept recept = new Recept(receptInfo,sastojci.getValue());
        repository.addRecept(recept);
    }

    public void onDeleteSastojak(int position){
        sastojci.getValue().remove(position);
    }
}
