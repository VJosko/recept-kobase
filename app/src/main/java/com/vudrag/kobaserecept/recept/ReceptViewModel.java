package com.vudrag.kobaserecept.recept;

import android.annotation.SuppressLint;
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
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class ReceptViewModel extends ViewModel {

    private Context context;
    private int position;
    MutableLiveData<Integer> onReturn = new MutableLiveData<>();
    MutableLiveData<ArrayList<Sastojak>> sastojci = new MutableLiveData<>();
    public String ime = "";
    Repository repository;

    public ReceptViewModel(int position) {
        this.position = position;
        repository = Repository.getInstance();
        onReturn.setValue(-1);
        if (position == -1) {
            ArrayList<Sastojak> s = new ArrayList<>();
            s.add(new Sastojak("", ""));
            sastojci.setValue(s);
        } else {
            ArrayList<Sastojak> s = new ArrayList<>(repository.getLiveRecept().getValue().get(position).getSastojci());
            sastojci.setValue(s);
            ime = repository.getLiveRecept().getValue().get(position).getReceptInfo().getIme();
        }
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void onAddSastojak() {
        Sastojak sastojak = new Sastojak("", "");
        sastojci.getValue().add(sastojak);
        sastojci.setValue(sastojci.getValue());
    }

    public void onSpremiRecept() {
        if (position == -1) {
            ReceptInfo receptInfo = new ReceptInfo(ime, getDate(), getDate(), "");
            Recept recept = new Recept(receptInfo, sastojci.getValue());
            repository.addRecept(recept);
        } else updateRecept();
        onReturn.setValue(1);
    }

    public void onDeleteSastojak(int position) {
        sastojci.getValue().remove(position);
    }

    public void updateRecept() {
        ReceptInfo receptInfo = repository.getLiveRecept().getValue().get(position).getReceptInfo();
        receptInfo.setIme(ime);
        receptInfo.setDatumIzmjene(getDate());
        repository.updateInfo(receptInfo);
        boolean exists = false;
        for (Sastojak s1 : repository.getLiveRecept().getValue().get(position).getSastojci()) {
            for (Sastojak s2 : sastojci.getValue()) {
                if (s2.getSastojakId() != null) {
                    if (s1.getSastojakId().equals(s2.getSastojakId())) {
                        exists = true;
                    }
                }
            }
            if (!exists) {
                repository.deleteSastojak(s1);
            }
            exists = false;
        }
        for (Sastojak sastojak : sastojci.getValue()) {
            if (sastojak.getId() == null) {
                sastojak.setId(repository.getLiveRecept().getValue().get(position).getReceptInfo().getId());
                repository.addSastojak(sastojak);
            } else repository.updateSastojak(sastojak);
        }
    }

    private String getDate(){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

}
