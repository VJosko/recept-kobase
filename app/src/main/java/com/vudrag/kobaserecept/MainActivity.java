package com.vudrag.kobaserecept;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;

public class MainActivity extends AppCompatActivity {

    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navController = Navigation.findNavController(this, R.id.fragment);
        NavigationUI.setupActionBarWithNavController(this, navController);

        Repository repository = Repository.getInstance();
        repository.setContext(getApplicationContext());

    }

    @Override
    public boolean onSupportNavigateUp() {
        UIUtil.hideKeyboard(this);
        navController.navigateUp();
        return super.onSupportNavigateUp();
    }
}