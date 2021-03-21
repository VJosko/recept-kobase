package com.vudrag.kobaserecept.kalkulator;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vudrag.kobaserecept.R;
import com.vudrag.kobaserecept.classes.Sastojak;
import com.vudrag.kobaserecept.databinding.FragmentKalkulatorBinding;

import java.util.ArrayList;


public class KalkulatorFragment extends Fragment {

    private KalkulatorViewModelFactory factory;
    private KalkulatorViewModel viewModel;

    private RecyclerView recyclerView;
    private recKalkulatorAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //DataBinding
        FragmentKalkulatorBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_kalkulator, container, false);
        View view = binding.getRoot();
        binding.setLifecycleOwner(getViewLifecycleOwner());

        //ViewModel
        factory = new KalkulatorViewModelFactory(KalkulatorFragmentArgs.fromBundle(getArguments()).getReceptId());
        viewModel = new ViewModelProvider(this, factory).get(KalkulatorViewModel.class);
        binding.setViewModel(viewModel);

        if (!viewModel.ime.equals(""))
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(viewModel.ime);

        //Recycler view
        recyclerView = view.findViewById(R.id.rec_kalkulator);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new recKalkulatorAdapter(viewModel.racunica.getValue());
        recyclerView.setAdapter(mAdapter);

        Observer<ArrayList<Sastojak>> racunicaObserver = sastojaks -> {
            mAdapter = new recKalkulatorAdapter(viewModel.racunica.getValue());
            recyclerView.setAdapter(mAdapter);
        };
        viewModel.racunica.observe(getViewLifecycleOwner(), racunicaObserver);

        return view;
    }
}
