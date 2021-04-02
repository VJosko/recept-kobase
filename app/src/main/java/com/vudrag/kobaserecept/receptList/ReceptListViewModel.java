package com.vudrag.kobaserecept.receptList;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.vudrag.kobaserecept.classes.Recept;
import com.vudrag.kobaserecept.Repository;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class ReceptListViewModel extends ViewModel {

    private Context context;
    Repository repository;

    public MutableLiveData<ArrayList<Recept>> recepti = new MutableLiveData<>();
    public LiveData<List<Recept>> _recepti;

    public ReceptListViewModel() {
        repository = Repository.getInstance();
        recepti.setValue(repository.getRecepte());
        _recepti = repository.getLiveRecept();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void updateReceptArray() {
        recepti.getValue().clear();
        //if (repository.getLiveRecept().getValue() != null)
        for (Recept recept : _recepti.getValue()) {
            recepti.getValue().add(recept);
        }
    }

    public void deleteRecept(int position) {
        repository.deleteRecept(recepti.getValue().get(position).getReceptInfo());
    }
}
