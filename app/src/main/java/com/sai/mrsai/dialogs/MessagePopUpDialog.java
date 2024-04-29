package com.sai.mrsai.dialogs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.sai.mrsai.R;

import java.util.Objects;

public class MessagePopUpDialog extends Dialog {
    private final Context context;
    private final String text;
    private final Integer textColor,imageResourceId;
    private final int duration;


    public MessagePopUpDialog(@NonNull Context context, @Nullable Integer textColor, String text, @Nullable Integer imageResourceId, int duration) {
        super(context);
        this.context = context;
        this.textColor = textColor;
        this.text = text;
        this.imageResourceId = imageResourceId;
        this.duration = duration;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.message_popup);

        ImageView icon = findViewById(R.id.notify_icon);
        TextView msg = findViewById(R.id.message);

        getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);

        if (imageResourceId != null){
            icon.setVisibility(View.VISIBLE);
            icon.setImageResource(imageResourceId);
        }else{
            icon.setVisibility(View.GONE);
        }
        msg.setText(text);
        if (textColor != null){
            msg.setTextColor(textColor);
        }
        Handler handler = new Handler();
        Runnable runnable = this::dismiss;
        handler.postDelayed(runnable,duration);
    }
}
