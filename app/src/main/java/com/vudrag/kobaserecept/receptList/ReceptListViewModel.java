package com.vudrag.kobaserecept.receptList;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.vudrag.kobaserecept.ReceptInfo;

import java.util.ArrayList;

public class ReceptListViewModel extends ViewModel {

    public void onAdd(){
        Log.d("TAG", "onAdd: ________________");
    }

    public MutableLiveData<ArrayList<ReceptInfo >> recepti = new MutableLiveData<>();

    public ReceptListViewModel() {
        ArrayList<ReceptInfo> lRecepti = new ArrayList<>();
        lRecepti.add(new ReceptInfo(1,"Prvi recept","1.1.1.","2.2.2","nema"));
        lRecepti.add(new ReceptInfo(1,"Prvi recept","1.1.1.","2.2.2","nema"));
        lRecepti.add(new ReceptInfo(1,"Prvi recept","1.1.1.","2.2.2","nema"));
        lRecepti.add(new ReceptInfo(1,"Prvi recept","1.1.1.","2.2.2","nema"));
        lRecepti.add(new ReceptInfo(1,"Prvi recept","1.1.1.","2.2.2","nema"));
        lRecepti.add(new ReceptInfo(1,"Prvi recept","1.1.1.","2.2.2","nema"));
        lRecepti.add(new ReceptInfo(1,"Prvi recept","1.1.1.","2.2.2","nema"));
        lRecepti.add(new ReceptInfo(1,"Prvi recept","1.1.1.","2.2.2","nema"));
        lRecepti.add(new ReceptInfo(1,"Prvi recept","1.1.1.","2.2.2","nema"));
        recepti.setValue(lRecepti);
    }
}
