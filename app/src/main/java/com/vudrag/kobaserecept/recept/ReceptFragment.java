package com.vudrag.kobaserecept.recept;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vudrag.kobaserecept.R;
import com.vudrag.kobaserecept.databinding.FragmentReceptiBinding;
import com.vudrag.kobaserecept.ReceptListItemTouchHelper;


public class ReceptFragment extends Fragment implements recReceptAdapter.OnSastojakListener {

    private ReceptViewModel viewModel;

    private RecyclerView recyclerView;
    private recReceptAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //DataBinding
        FragmentReceptiBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_recepti,container,false);
        View view = binding.getRoot();

        //ViewModel
        viewModel = new ViewModelProvider(this).get(ReceptViewModel.class);
        binding.setViewModel(viewModel);

        //Recycler view
        recyclerView = view.findViewById(R.id.rec_novi_recept);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new recReceptAdapter(viewModel.sastojci.getValue(), this);

        ItemTouchHelper.Callback callback = new ReceptListItemTouchHelper(mAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        mAdapter.setTouchHelper(itemTouchHelper);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        recyclerView.setAdapter(mAdapter);


        viewModel.sastojci.observe(getViewLifecycleOwner(),o -> mAdapter.notifyDataSetChanged());

        return view;
    }

    @Override
    public void onSastojakDelete(int position) {
        viewModel.onDeleteSastojak(position);
    }
}