package com.vudrag.kobaserecept.receptList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vudrag.kobaserecept.R;
import com.vudrag.kobaserecept.classes.Recept;

import java.util.ArrayList;

public class recReceptListAdapter extends RecyclerView.Adapter<recReceptListAdapter.ViewHolder> {
    private ArrayList<Recept> recepti;
    private OnReceptListener mOnReceptiListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvIme, tvDatum;
        OnReceptListener onReceptListener;

        public ViewHolder(@NonNull View view, OnReceptListener onReceptListener) {
            super(view);
            tvIme = view.findViewById(R.id.tv_ime);
            tvDatum = view.findViewById(R.id.tv_datum);
            this.onReceptListener = onReceptListener;

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onReceptListener.onReceptClick(getAdapterPosition());
        }
    }

    public recReceptListAdapter(ArrayList<Recept> recepti, OnReceptListener onReceptListener){
        this.recepti = recepti;
        this.mOnReceptiListener = onReceptListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recept_list_row, parent, false);
        return new ViewHolder(view, mOnReceptiListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvIme.setText(recepti.get(position).getReceptInfo().getIme());
        holder.tvDatum.setText(recepti.get(position).getReceptInfo().getDatumIzmjene());
    }

    @Override
    public int getItemCount() {
        return recepti.size();
    }

    public interface OnReceptListener{
        void onReceptClick(int position);
    }

}
