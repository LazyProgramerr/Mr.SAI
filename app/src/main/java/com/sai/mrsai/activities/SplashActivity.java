package com.sai.mrsai.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.sai.mrsai.MainActivity;
import com.sai.mrsai.R;
import com.sai.mrsai.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}