package com.vudrag.kobaserecept;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.vudrag.kobaserecept.ItemTouchHelperAdapter;
import com.vudrag.kobaserecept.R;

public class ReceptListItemTouchHelper extends ItemTouchHelper.Callback {

    private final ItemTouchHelperAdapter adapter;

    public ReceptListItemTouchHelper(ItemTouchHelperAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
//        viewHolder.itemView.setBackgroundColor(
//                ContextCompat.getColor(viewHolder.itemView.getContext(), R.color.design_default_color_primary_dark)
//        );
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
//        if(actionState == ItemTouchHelper.ACTION_STATE_DRAG){
//            viewHolder.itemView.setBackgroundColor(
//                    ContextCompat.getColor(viewHolder.itemView.getContext(), R.color.black)
//            );
//        }
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        final int swipeFlags = ItemTouchHelper.START;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        adapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        adapter.onItemSwiped(viewHolder.getAdapterPosition());
    }

    
}
