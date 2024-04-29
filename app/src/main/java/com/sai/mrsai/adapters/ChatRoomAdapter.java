package com.sai.mrsai.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sai.mrsai.R;
import com.sai.mrsai.functions.Token;
import com.sai.mrsai.models.ChatRoomModel;

import java.util.List;

public class ChatRoomAdapter extends RecyclerView.Adapter<ChatRoomAdapter.ChatRoomViewHolder> {

    Context context;
    List<ChatRoomModel> chatRoomModelList;
    GestureDetector gestureDetector;

    public ChatRoomAdapter(Context context, List<ChatRoomModel> chatRoomModelList) {
        this.context = context;
        this.chatRoomModelList = chatRoomModelList;
    }

    @NonNull
    @Override
    public ChatRoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_chat_user,parent,false);
        return new ChatRoomViewHolder(view);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull ChatRoomViewHolder holder, int position) {

        holder.chatRoomImage.setOnClickListener(v->{
            Toast.makeText(context,String.valueOf(position),Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    public int getItemCount() {return chatRoomModelList.size()+16;}

    static class ChatRoomViewHolder extends RecyclerView.ViewHolder {

        CardView chatRoomHolder;
        TextView chatRoomName, chatRoomLastSenderName, chatRoomLastMessage, lastChatStatusIcon;
        LinearLayout chatRoomLastData;
        ImageView chatRoomImage;
        GestureDetector gestureDetector;
        boolean isDataEnabled;

        @SuppressLint("ClickableViewAccessibility")
        public ChatRoomViewHolder(@NonNull View itemView) {
            super(itemView);
            chatRoomHolder = itemView.findViewById(R.id.chatRoomHolder);
            chatRoomName = itemView.findViewById(R.id.chatRoomName);
            chatRoomImage = itemView.findViewById(R.id.chatRoomImage);
            chatRoomLastData = itemView.findViewById(R.id.chatRoomLastData);
            chatRoomLastSenderName = itemView.findViewById(R.id.chatRoomLastSenderName);
            chatRoomLastMessage = itemView.findViewById(R.id.chatRoomLastMessage);
            lastChatStatusIcon = itemView.findViewById(R.id.lastMessageStatus);

            // Initialize gestureDetector for this ViewHolder
            gestureDetector = new GestureDetector(itemView.getContext(), new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onDoubleTap(@NonNull MotionEvent e) {
                    toggleLastDataVisibility();
                    return true;
                }
            });

            // Handle touch events within this ViewHolder
            itemView.setOnTouchListener((v, event) -> {
                gestureDetector.onTouchEvent(event);
                return true;
            });
        }

        private void toggleLastDataVisibility() {
            isDataEnabled = !isDataEnabled;
            chatRoomLastData.setVisibility(isDataEnabled ? View.VISIBLE : View.GONE);
//            updateLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        private void updateLayoutParams(int height) {
            ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
            layoutParams.height = height;
            itemView.setLayoutParams(layoutParams);
        }
    }
}
