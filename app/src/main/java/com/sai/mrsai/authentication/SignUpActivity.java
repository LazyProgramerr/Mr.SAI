package com.sai.mrsai.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sai.mrsai.MainActivity;
import com.sai.mrsai.R;
import com.sai.mrsai.databinding.ActivitySignUpBinding;
import com.sai.mrsai.functions.Token;
import com.sai.mrsai.managers.SharedPreferenceManager;
import com.sai.mrsai.models.UserDetails;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    FirebaseAuth firebaseAuth;
    FirebaseUser fUser;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseAuth = FirebaseAuth.getInstance();

        binding.showPasswordText.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_UP -> {
                    binding.passwordInput1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    binding.passwordInput2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    binding.showPasswordText.setTextColor(getResources().getColor(R.color.orange));
                }
                case MotionEvent.ACTION_DOWN -> {
                    binding.passwordInput1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    binding.passwordInput2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    binding.showPasswordText.setTextColor(getResources().getColor(R.color.cream));
                }
            }
            return true;
        });

        binding.signUp.setOnClickListener(v -> {
            String uName = binding.userNameInput.getText().toString().trim();
            String email = binding.emailInput.getText().toString().trim();
            String pNumber = binding.numberInput.getText().toString().trim();
            String password1 = binding.passwordInput1.getText().toString().trim();
            String password2 = binding.passwordInput2.getText().toString().trim();

            if (password1.equals(password2) && !uName.isEmpty() && !email.isEmpty() && !pNumber.isEmpty()){
                createAccount(uName,email,pNumber,password1);
            }
        });
    }

    private void createAccount(String uName, String email, String pNumber, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                fUser= firebaseAuth.getCurrentUser();
                Token.getToken(this,token -> {
                    DatabaseReference dr = FirebaseDatabase.getInstance().getReference("Users");
                    HashMap<String,String> uData = new HashMap<>();
                    uData.put("uId",fUser.getUid());
                    uData.put("Name",uName);
                    uData.put("Phone",pNumber);
                    uData.put("userState","online");
                    uData.put("eMail",email);
                    uData.put("password",password);
                    uData.put("userImg","imageUrl");
                    uData.put("Token",token);
                    dr.child(fUser.getUid()).setValue(uData).addOnCompleteListener(task1 -> {
                        if (task.isSuccessful()){
                            SharedPreferenceManager.saveLoginStatus(this,true);
                            SharedPreferenceManager.saveUserData(this,fUser.getUid(),uName,email,pNumber);
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                    });
                });


            }
        }).addOnFailureListener(e -> {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }
}