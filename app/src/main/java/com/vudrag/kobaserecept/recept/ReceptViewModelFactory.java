package com.vudrag.kobaserecept.recept;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.vudrag.kobaserecept.kalkulator.KalkulatorViewModel;

public class ReceptViewModelFactory implements ViewModelProvider.Factory {

    private int position;

    public ReceptViewModelFactory(int position) {
        this.position = position;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(ReceptViewModel.class)){
            return (T) new ReceptViewModel(position);
        }
        throw new IllegalArgumentException("Uknown ViewModel class");
    }

}
