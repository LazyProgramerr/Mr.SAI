package com.sai.mrsai.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.sai.mrsai.MainActivity;
import com.sai.mrsai.R;
import com.sai.mrsai.databinding.ActivityLoginBinding;
import com.sai.mrsai.dialogs.MessagePopUpDialog;
import com.sai.mrsai.managers.SharedPreferenceManager;

public class LoginActivity extends AppCompatActivity {
    
    private ActivityLoginBinding binding;
    FirebaseAuth firebaseAuth;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();


        binding.clickMe.setOnTouchListener((v, event) -> {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    binding.clickMe.setTextColor(getResources().getColor(R.color.cream));
                    break;
                case MotionEvent.ACTION_UP:
                    binding.clickMe.setTextColor(getResources().getColor(R.color.orange));
                    break;
            }
            return true;
        });
        binding.showPasswordText.setOnTouchListener((v, event) -> {
            switch (event.getAction()){
                case MotionEvent.ACTION_UP:
                    binding.passwordInput.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    binding.showPasswordText.setTextColor(getResources().getColor(R.color.orange));
                    break;
                case MotionEvent.ACTION_DOWN:
                    binding.passwordInput.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    binding.showPasswordText.setTextColor(getResources().getColor(R.color.cream));
                    break;
            }
            return true;
        });

        binding.login.setOnClickListener(v -> {
            String mail = binding.emailInput.getText().toString().trim();
            String password = binding.passwordInput.getText().toString().trim();
            if (mail.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "please enter valid details", Toast.LENGTH_SHORT).show();
            }else{
                signInWithMail(mail,password);
            }
        });
    }

    private void signInWithMail(String mail, String password) {
        firebaseAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                SharedPreferenceManager.saveLoginStatus(this,true);
                Toast.makeText(this, "login Success", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            } else if (task.isCanceled()) {
                Toast.makeText(this, "login cancelled by the user", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "login failed check your connection and try again", Toast.LENGTH_SHORT).show();
            }
        });
    }
}