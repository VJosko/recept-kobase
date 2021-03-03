package com.vudrag.kobaserecept.receptList;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.vudrag.kobaserecept.Recept;
import com.vudrag.kobaserecept.ReceptInfo;
import com.vudrag.kobaserecept.Repository;

import java.util.ArrayList;

public class ReceptListViewModel extends ViewModel {

    Repository repository;

    public MutableLiveData<ArrayList<Recept>> recepti = new MutableLiveData<>();

    public ReceptListViewModel() {
        repository = Repository.getInstance();
        recepti.setValue(repository.getRecepte());
    }
}
