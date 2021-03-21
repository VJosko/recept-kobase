package com.vudrag.kobaserecept.receptList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.vudrag.kobaserecept.R;
import com.vudrag.kobaserecept.ReceptListItemTouchHelper;
import com.vudrag.kobaserecept.databinding.FragmentReceptListBinding;

import static android.content.ContentValues.TAG;


public class ReceptListFragment extends Fragment implements recReceptListAdapter.OnReceptListener {

    private ReceptListViewModel viewModel;

    private RecyclerView recyclerView;
    private recReceptListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //dataBinding
        FragmentReceptListBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_recept_list,container,false);
        View view = binding.getRoot();

        //ViewModel
        viewModel = new ViewModelProvider(this).get(ReceptListViewModel.class);
        viewModel.setContext(getContext().getApplicationContext());
        binding.setViewModel(viewModel);

        binding.btnNewRecept.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_receptListFragment_to_receptFragment);
        });

        //RecyclerView
        recyclerView = view.findViewById(R.id.rec_recepti);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        mAdapter = new recReceptListAdapter(viewModel.recepti.getValue(), this);

        ItemTouchHelper.Callback callback = new ReceptListItemTouchHelper(mAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        mAdapter.setTouchHelper(itemTouchHelper);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        recyclerView.setAdapter(mAdapter);


        Observer receptObserver = o -> {
            viewModel.updateReceptArray();
            mAdapter.notifyDataSetChanged();
        };
        viewModel._recepti.observe(getViewLifecycleOwner(), receptObserver);

        return view;
    }

    @Override
    public void onReceptClick(int position) {
        ReceptListFragmentDirections.ActionReceptListFragmentToKalkulatorFragment action = ReceptListFragmentDirections.actionReceptListFragmentToKalkulatorFragment();
        action.setReceptId(position);
        Navigation.findNavController(getView()).navigate(action);
    }

    @Override
    public void onReceptDelete(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Želite li izbrisati ovaj recept?");
        builder.setTitle("Izbriši?");
        builder.setPositiveButton("DA", (dialog, which) -> {
            viewModel.deleteRecept(position);
        });
        builder.setNegativeButton("NE", (dialog, which) -> {
            mAdapter.notifyItemChanged(position);
        });
        builder.setOnCancelListener(dialog -> {
            mAdapter.notifyItemChanged(position);
        });
        builder.create().show();
    }

    @Override
    public void onReceptEdit(int position) {
        ReceptListFragmentDirections.ActionReceptListFragmentToReceptFragment action = ReceptListFragmentDirections.actionReceptListFragmentToReceptFragment();
        action.setPosition(position);
        Navigation.findNavController(getView()).navigate(action);
    }


}