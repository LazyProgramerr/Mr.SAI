package com.sai.mrsai.functions;

import android.content.Context;

import com.google.firebase.messaging.FirebaseMessaging;

public class Token {
    public interface TokenCallback {
        void onTokenReceived(String token);
    }

    public static void getToken(Context context, TokenCallback callback) {

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
//                    LoadingDialog.dismiss();
                    if (!task.isSuccessful()) {
                        if (callback != null) {
                            callback.onTokenReceived(""); // Notify the callback with an empty token
                        }
                        return;
                    }

                    String token = task.getResult();
                    if (callback != null) {
                        callback.onTokenReceived(token); // Notify the callback with the received token
                    }
                });
    }
}
