package com.sai.mrsai.managers;

import android.content.Context;
import android.content.SharedPreferences;

import com.sai.mrsai.functions.Constants;
import com.sai.mrsai.models.UserDetails;
import com.sai.mrsai.models.UserLogin;

public class SharedPreferenceManager {

    public static void saveLoginStatus(Context context, boolean loginStatus){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SHARED_PREFS_LOGIN_STATUS,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("loginStatus",loginStatus);
        editor.apply();
    }
    public static UserLogin getLoginStatus(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SHARED_PREFS_LOGIN_STATUS,Context.MODE_PRIVATE);
        boolean lStat = sharedPreferences.getBoolean("loginStatus",false);
        return new UserLogin(lStat);
    }

    public static void saveUserData(Context context, String uid,String name,String email,String phone){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SHARED_PREFS_USER_DATA,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userId",uid);
        editor.putString("userName",name);
        editor.putString("userMail",email);
        editor.putString("userPhone",phone);
        editor.apply();
    }
    public static UserDetails getUserData(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SHARED_PREFS_USER_DATA,Context.MODE_PRIVATE);
        String uName = sharedPreferences.getString("userName","");
        String uMail = sharedPreferences.getString("userMail","");
        String uPhone = sharedPreferences.getString("userPhone","");
        String uid = sharedPreferences.getString("userId","");
        return new UserDetails(uid,uName,uMail,uPhone);
    }
}
