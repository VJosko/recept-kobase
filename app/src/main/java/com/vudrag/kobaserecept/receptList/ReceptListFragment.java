package com.vudrag.kobaserecept.receptList;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vudrag.kobaserecept.R;
import com.vudrag.kobaserecept.ReceptInfo;
import com.vudrag.kobaserecept.databinding.FragmentReceptListBinding;
import com.vudrag.kobaserecept.recept.recReceptAdapter;

import java.util.ArrayList;
import java.util.Observable;


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
        binding.setViewModel(viewModel);

        binding.btnNewRecept.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_receptListFragment_to_receptFragment);
        });

        //RecyclerView
        recyclerView = view.findViewById(R.id.rec_recepti);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new recReceptListAdapter(viewModel.recepti.getValue(), this);
        recyclerView.setAdapter(mAdapter);

        Observer receptObserver = o -> {
            mAdapter.notifyDataSetChanged();
        };
        viewModel.recepti.observe(getViewLifecycleOwner(), receptObserver);

        return view;
    }

    @Override
    public void onReceptClick(int position) {
        Navigation.findNavController(getView()).navigate(R.id.action_receptListFragment_to_kalkulatorFragment);
    }
}