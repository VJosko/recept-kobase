package com.vudrag.kobaserecept.recept;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
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

import com.vudrag.kobaserecept.R;
import com.vudrag.kobaserecept.databinding.FragmentReceptiBinding;
import com.vudrag.kobaserecept.ReceptListItemTouchHelper;

import static android.content.ContentValues.TAG;


public class ReceptFragment extends Fragment implements recReceptAdapter.OnSastojakListener {

    private ReceptViewModel viewModel;
    private ReceptViewModelFactory factory;

    private RecyclerView recyclerView;
    private recReceptAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        int position = ReceptFragmentArgs.fromBundle(getArguments()).getPosition();

        //DataBinding
        FragmentReceptiBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recepti, container, false);
        View view = binding.getRoot();

        //ViewModel
        factory = new ReceptViewModelFactory(position);
        viewModel = new ViewModelProvider(this, factory).get(ReceptViewModel.class);
        binding.setViewModel(viewModel);

        if (viewModel.ime.equals("")) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Novi recept");
        } else
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Uređivanje recepta");


        //Recycler view
        recyclerView = view.findViewById(R.id.rec_novi_recept);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        mAdapter = new recReceptAdapter(viewModel.sastojci.getValue(), this);

        ItemTouchHelper.Callback callback = new ReceptListItemTouchHelper(mAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        mAdapter.setTouchHelper(itemTouchHelper);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        recyclerView.setAdapter(mAdapter);


        viewModel.sastojci.observe(getViewLifecycleOwner(), o -> mAdapter.notifyDataSetChanged());

        viewModel.onReturn.observe(getViewLifecycleOwner(), o -> onReturn());

        return view;
    }

    private void onReturn() {
        if (viewModel.onReturn.getValue() == 1) {
            Navigation.findNavController(getView()).navigateUp();
        }
    }

    @Override
    public void onSastojakDelete(int position) {
        viewModel.onDeleteSastojak(position);
    }
}