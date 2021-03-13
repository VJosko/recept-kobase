package com.vudrag.kobaserecept.receptList;

import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.vudrag.kobaserecept.ItemTouchHelperAdapter;
import com.vudrag.kobaserecept.R;
import com.vudrag.kobaserecept.classes.Recept;
import com.vudrag.kobaserecept.databinding.ReceptListRowBinding;
import com.vudrag.kobaserecept.databinding.ReceptRowBinding;

import java.util.ArrayList;

public class recReceptListAdapter extends RecyclerView.Adapter<recReceptListAdapter.ViewHolder> implements
        ItemTouchHelperAdapter {

    private ArrayList<Recept> recepti;
    private OnReceptListener mOnReceptiListener;
    private ItemTouchHelper touchHelper;

    public class ViewHolder extends RecyclerView.ViewHolder implements
            View.OnTouchListener,
            GestureDetector.OnGestureListener {

        OnReceptListener onReceptListener;
        GestureDetector gestureDetector;
        ReceptListRowBinding binding;

        public ViewHolder(@NonNull ReceptListRowBinding binding, OnReceptListener onReceptListener) {
            super(binding.getRoot());
            this.onReceptListener = onReceptListener;
            this.binding = binding;

            binding.btnEdit.setOnClickListener(v -> onReceptListener.onReceptEdit(getAdapterPosition()));

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
            onReceptListener.onReceptClick(getAdapterPosition());
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

    public recReceptListAdapter(ArrayList<Recept> recepti, OnReceptListener onReceptListener){
        this.recepti = recepti;
        this.mOnReceptiListener = onReceptListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ReceptListRowBinding binding = ReceptListRowBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding, mOnReceptiListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recept recept = recepti.get(position);
        holder.binding.setRecept(recept);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return recepti.size();
    }

    public interface OnReceptListener{
        void onReceptClick(int position);
        void onReceptDelete(int position);
        void onReceptEdit(int position);
    }


    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Recept recept = recepti.get(fromPosition);
        recepti.remove(recept);
        recepti.add(toPosition, recept);
        notifyItemMoved(fromPosition,toPosition);
    }

    @Override
    public void onItemSwiped(int position) {
        mOnReceptiListener.onReceptDelete(position);
    }

    public void setTouchHelper(ItemTouchHelper touchHelper){
        this.touchHelper = touchHelper;
    }

}
