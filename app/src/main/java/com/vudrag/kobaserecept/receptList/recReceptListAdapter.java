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

import java.util.ArrayList;

public class recReceptListAdapter extends RecyclerView.Adapter<recReceptListAdapter.ViewHolder> implements
        ItemTouchHelperAdapter {

    private ArrayList<Recept> recepti;
    private OnReceptListener mOnReceptiListener;
    private ItemTouchHelper touchHelper;

    public class ViewHolder extends RecyclerView.ViewHolder implements
            View.OnTouchListener,
            GestureDetector.OnGestureListener {

        private TextView tvIme, tvDatum;
        OnReceptListener onReceptListener;
        GestureDetector gestureDetector;

        public ViewHolder(@NonNull View view, OnReceptListener onReceptListener) {
            super(view);
            tvIme = view.findViewById(R.id.tv_ime);
            tvDatum = view.findViewById(R.id.tv_datum);
            this.onReceptListener = onReceptListener;

            gestureDetector = new GestureDetector(view.getContext(), this);

            view.setOnTouchListener(this);
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
        void onReceptDelete(int position);
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
