package com.vudrag.kobaserecept.recept;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.textfield.TextInputLayout;
import com.vudrag.kobaserecept.R;
import com.vudrag.kobaserecept.databinding.FragmentReceptiBinding;
import com.vudrag.kobaserecept.ReceptListItemTouchHelper;

import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;

import java.lang.reflect.Field;

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

        setHasOptionsMenu(true);

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
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.save_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_save) {
            UIUtil.hideKeyboard(getActivity());
            viewModel.onSpremiRecept();
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onSastojakDelete(int position) {
        viewModel.onDeleteSastojak(position);
    }
}