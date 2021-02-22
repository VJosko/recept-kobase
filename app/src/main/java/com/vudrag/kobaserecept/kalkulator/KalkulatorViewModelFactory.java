package com.vudrag.kobaserecept.kalkulator;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class KalkulatorViewModelFactory implements ViewModelProvider.Factory {

    private int receptId;

    public KalkulatorViewModelFactory(int receptId) {
        this.receptId = receptId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(KalkulatorViewModel.class)){
            return (T) new KalkulatorViewModel(receptId);
        }
        throw new IllegalArgumentException("Uknown ViewModel class");
    }

}
