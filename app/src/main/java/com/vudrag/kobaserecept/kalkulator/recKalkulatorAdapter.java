package com.vudrag.kobaserecept.kalkulator;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vudrag.kobaserecept.classes.Sastojak;
import com.vudrag.kobaserecept.databinding.KalkulatorRowBinding;

import java.util.ArrayList;

public class recKalkulatorAdapter extends RecyclerView.Adapter<recKalkulatorAdapter.ViewHolder> {
    ArrayList<Sastojak> sastojci;

    public class ViewHolder extends RecyclerView.ViewHolder {
        KalkulatorRowBinding kalkulatorRowBinding;

        public ViewHolder(@NonNull KalkulatorRowBinding kalkulatorRowBinding) {
            super(kalkulatorRowBinding.getRoot());
            this.kalkulatorRowBinding = kalkulatorRowBinding;
        }
    }

    public recKalkulatorAdapter(ArrayList<Sastojak> sastojci){
        this.sastojci = sastojci;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        KalkulatorRowBinding kalkulatorRowBinding = KalkulatorRowBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(kalkulatorRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Sastojak sastojak = sastojci.get(position);
        holder.kalkulatorRowBinding.setSastojak(sastojak);
        holder.kalkulatorRowBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return sastojci.size();
    }
}
