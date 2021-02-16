package com.example.plutoacademy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;

import com.example.plutoacademy.databinding.LoadingdialogBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // controls the view
        BottomNavigationView navView = findViewById(R.id.navigation);
        // Passing each menu ID as a set of Ids because each


        // builds navGraph
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_books, R.id.navigation_experts, R.id.navigation_about)
                .build();
        // controls the fragment-to-fragment navigation in activity
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        // set the UI
        NavigationUI.setupWithNavController(navView, navController);
    }




}