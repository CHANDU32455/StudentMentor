package com.nbkr.studentmentor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        // Check if user is logged in
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            Log.e("MainActivity", "User not logged in, redirecting to login.");
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        // Set up Navigation Component
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Ensure NavController is retrieved from the NavHostFragment
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);

        if (navHostFragment != null) {
            NavController navController = navHostFragment.getNavController();
            NavigationUI.setupWithNavController(bottomNavigationView, navController);
        } else {
            Log.e("MainActivity", "NavHostFragment is null. Check activity_main.xml.");
        }
    }
}
