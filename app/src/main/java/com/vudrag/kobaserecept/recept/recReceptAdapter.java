package com.vudrag.kobaserecept.recept;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vudrag.kobaserecept.Sastojak;
import com.vudrag.kobaserecept.databinding.ReceptRowBinding;

import java.util.ArrayList;

public class recReceptAdapter extends RecyclerView.Adapter<recReceptAdapter.ViewHolder> {
    ArrayList<Sastojak> sastojci;

    public class ViewHolder extends RecyclerView.ViewHolder {

        ReceptRowBinding binding;

        public ViewHolder(@NonNull ReceptRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public recReceptAdapter(ArrayList<Sastojak> sastojci){
        this.sastojci = sastojci;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ReceptRowBinding binding = ReceptRowBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Sastojak sastojak = sastojci.get(position);
        holder.binding.setSastojak(sastojak);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return sastojci.size();
    }
}
