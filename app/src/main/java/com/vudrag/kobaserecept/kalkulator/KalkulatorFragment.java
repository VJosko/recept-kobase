package com.vudrag.kobaserecept.kalkulator;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vudrag.kobaserecept.R;
import com.vudrag.kobaserecept.databinding.FragmentKalkulatorBinding;


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
        FragmentKalkulatorBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_kalkulator,container,false);
        View view = binding.getRoot();

        //ViewModel
        factory = new KalkulatorViewModelFactory(1);
        viewModel = new ViewModelProvider(this, factory).get(KalkulatorViewModel.class);
        binding.setViewModel(viewModel);

//        //Recycler view
//        recyclerView = view.findViewById(R.id.rec_kalkulator);
//        layoutManager = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(layoutManager);
//        mAdapter = new KalkulatorAdapter(racunica);
//        recyclerView.setAdapter(mAdapter);

        return view;
    }
}
