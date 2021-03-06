package com.vudrag.kobaserecept;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.vudrag.kobaserecept.classes.Recept;
import com.vudrag.kobaserecept.classes.Sastojak;
import com.vudrag.kobaserecept.room.ReceptDatabase;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class Repository {

    private Context context;
    private ReceptDatabase receptDatabase;

    private static Repository instance = null;

    private ArrayList<Recept> recepti = new ArrayList<>();
    private LiveData<List<Recept>> _recepti;

    public static synchronized Repository getInstance(){
        if(instance == null){
            instance = new Repository();
        }
        return instance;
    }

    public void setContext(Context context){
        this.context = context;
        receptDatabase = ReceptDatabase.getInstance(context);
        _recepti = receptDatabase.dao().getRecepteList();
    }

    public void addRecept(Recept recept){
        //recepti.add(recept);
        long id;
        id = receptDatabase.dao().insertInfo(recept.getReceptInfo());
        for (Sastojak sastojak: recept.getSastojci()) {
            sastojak.setId(id);
            receptDatabase.dao().insertSastojak(sastojak);
        }
    }

    public ArrayList<Recept> getRecepte(){
        return recepti;
    }

    public LiveData<List<Recept>> getLiveRecept(){
        return _recepti;
    }
}