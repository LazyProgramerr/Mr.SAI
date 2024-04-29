package com.sai.mrsai;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.sai.mrsai.activities.HomeActivity;
import com.sai.mrsai.authentication.LoginActivity;
import com.sai.mrsai.authentication.SignUpActivity;
import com.sai.mrsai.managers.SharedPreferenceManager;
import com.sai.mrsai.models.UserLogin;

import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
public class MainActivity extends AppCompatActivity {
    private String[] permissions = {
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.POST_NOTIFICATIONS,
            Manifest.permission.DETECT_SCREEN_CAPTURE,
            Manifest.permission.BLUETOOTH
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkAndRequestPermission();

    }
    public void test(View view) {
        UserLogin login = SharedPreferenceManager.getLoginStatus(this);

        if (login.getLoginStatus()){
            Intent welcomeIntent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(welcomeIntent);
        }else {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
            View view1 = View.inflate(this,R.layout.bottom_popup_ls,null);
            bottomSheetDialog.setContentView(view1);
            bottomSheetDialog.show();

            Button loginBtn = view1.findViewById(R.id.login_btn);
            Button signupBtn = view1.findViewById(R.id.sign_up_btn);
            loginBtn.setOnClickListener(v -> {
                Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(loginIntent);
                bottomSheetDialog.cancel();
            });
            signupBtn.setOnClickListener(v -> {
                Intent signupIntent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(signupIntent);
                bottomSheetDialog.cancel();
            });
        }
    }
    private void checkAndRequestPermission() {
        List<String> notGrantedPermissions = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                notGrantedPermissions.add(permission);
            }
        }
        if (!notGrantedPermissions.isEmpty()) {
            ActivityCompat.requestPermissions(this, notGrantedPermissions.toArray(new String[0]), 123);
        }
    }
}