package com.vudrag.kobaserecept.kalkulator;

import android.app.Application;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputLayout;
import com.vudrag.kobaserecept.R;
import com.vudrag.kobaserecept.classes.Sastojak;
import com.vudrag.kobaserecept.databinding.FragmentKalkulatorBinding;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;


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


        try {
            int nightModeFlags = getContext().getResources().getConfiguration().uiMode &
                    Configuration.UI_MODE_NIGHT_MASK;
            int defaultColor = R.color.purple_200;
            int focusedColor = R.color.teal_200;
            switch (nightModeFlags){
                case Configuration.UI_MODE_NIGHT_YES:
                    defaultColor = R.color.purple_200;
                    focusedColor = R.color.teal_200;
                    break;
                case Configuration.UI_MODE_NIGHT_NO:
                    defaultColor = R.color.purple_200;
                    focusedColor = R.color.teal_200;
                    break;
            }
            TextInputLayout textInputLayout = view.findViewById(R.id.txt_input_layout);
            Field field = TextInputLayout.class.getDeclaredField("defaultStrokeColor");
            field.setAccessible(true);
            field.set(textInputLayout, ContextCompat.getColor(view.getContext(), defaultColor));
            Field field1 = TextInputLayout.class.getDeclaredField("focusedStrokeColor");
            field1.setAccessible(true);
            field1.set(textInputLayout, ContextCompat.getColor(view.getContext(), defaultColor));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

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

        Observer<String> mesoObserver = meso -> {
            viewModel.racunanje();
        };
        viewModel.meso.observe(getViewLifecycleOwner(), mesoObserver);

        return view;
    }
}
