package com.sai.mrsai.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.sai.mrsai.MainActivity;
import com.sai.mrsai.R;
import com.sai.mrsai.databinding.ActivityHomeBinding;
import com.sai.mrsai.fragments.ChatsFragment;
import com.sai.mrsai.fragments.HomeFragment;
import com.sai.mrsai.fragments.ProfileFragment;
import com.sai.mrsai.fragments.TasksFragment;
import com.sai.mrsai.managers.SharedPreferenceManager;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.topPanelOptions.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            SharedPreferenceManager.saveLoginStatus(this,false);
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        });

        FragmentManager fragmentManager = getSupportFragmentManager();

        HomeFragment homeFragment = new HomeFragment();
        fragmentManager.beginTransaction().replace(R.id.fragmentsContainer,homeFragment).commit();
        binding.bottomNavigationBar.setOnItemSelected(integer -> {
            switch (integer) {
                case 0 -> {
                    HomeFragment homeFragment1 = new HomeFragment();
                    fragmentManager.beginTransaction().replace(R.id.fragmentsContainer, homeFragment1).commitAllowingStateLoss();
                    binding.topPanelText.setText(R.string.app_name);
                }
                case 1 -> {
                    ChatsFragment chatsFragment = new ChatsFragment();
                    fragmentManager.beginTransaction().replace(R.id.fragmentsContainer, chatsFragment).commitAllowingStateLoss();
                    binding.topPanelText.setText("Chats");
                }
                case 2 -> {
                    TasksFragment groupsFragment = new TasksFragment();
                    fragmentManager.beginTransaction().replace(R.id.fragmentsContainer, groupsFragment).commitAllowingStateLoss();
                    binding.topPanelText.setText("Tasks");
                }
                case 3 ->{
                    ProfileFragment userFragment = new ProfileFragment();
                    fragmentManager.beginTransaction().replace(R.id.fragmentsContainer,userFragment).commit();
                    binding.topPanelText.setText("Profile");
                }
            }
            return null;
        });
    }
}