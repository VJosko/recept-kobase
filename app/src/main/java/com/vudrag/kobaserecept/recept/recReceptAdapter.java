package com.vudrag.kobaserecept.recept;

import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.vudrag.kobaserecept.ItemTouchHelperAdapter;
import com.vudrag.kobaserecept.classes.Recept;
import com.vudrag.kobaserecept.classes.Sastojak;
import com.vudrag.kobaserecept.databinding.ReceptRowBinding;

import java.util.ArrayList;

public class recReceptAdapter extends RecyclerView.Adapter<recReceptAdapter.ViewHolder> implements
        ItemTouchHelperAdapter {

    ArrayList<Sastojak> sastojci;
    private OnSastojakListener onSastojakListener;
    private ItemTouchHelper touchHelper;

    public class ViewHolder extends RecyclerView.ViewHolder implements
            View.OnTouchListener,
            GestureDetector.OnGestureListener {

        ReceptRowBinding binding;
        OnSastojakListener onSastojakListener;
        GestureDetector gestureDetector;

        public ViewHolder(@NonNull ReceptRowBinding binding, OnSastojakListener onSastojakListener) {
            super(binding.getRoot());
            this.binding = binding;
            this.onSastojakListener = onSastojakListener;

            gestureDetector = new GestureDetector(binding.getRoot().getContext(), this);

            binding.getRoot().setOnTouchListener(this);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            //touchHelper.startDrag(this);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            gestureDetector.onTouchEvent(event);
            return true;
        }
    }

    public recReceptAdapter(ArrayList<Sastojak> sastojci, OnSastojakListener onSastojakListener){
        this.sastojci = sastojci;
        this.onSastojakListener = onSastojakListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ReceptRowBinding binding = ReceptRowBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding, onSastojakListener);
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

    public interface OnSastojakListener{
        void onSastojakDelete(int position);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Sastojak sastojak = sastojci.get(fromPosition);
        sastojci.remove(sastojak);
        sastojci.add(toPosition, sastojak);
        notifyItemMoved(fromPosition,toPosition);
    }

    @Override
    public void onItemSwiped(int position) {
        onSastojakListener.onSastojakDelete(position);
        notifyItemRemoved(position);
    }

    public void setTouchHelper(ItemTouchHelper touchHelper){
        this.touchHelper = touchHelper;
    }
}
