package com.sai.mrsai.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sai.mrsai.R;
import com.sai.mrsai.models.ChatRoomModel;
import com.sai.mrsai.models.UserDetails;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewsHolder> {

    Context context;
    List<UserDetails> userDetailsList;

    public SearchAdapter(Context context, List<UserDetails> userDetailsList) {
        this.context = context;
        this.userDetailsList = userDetailsList;
    }

    @NonNull
    @Override
    public SearchAdapter.SearchViewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_chat_user,null,false);
        return new SearchViewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.SearchViewsHolder holder, int position) {

    }

    @Override
    public int getItemCount() {return userDetailsList.size();}
    static class SearchViewsHolder extends RecyclerView.ViewHolder{
        CardView layout;
        ImageView userIcon;
        TextView userName;
        public SearchViewsHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.chatRoomHolder);
            userIcon = itemView.findViewById(R.id.chatRoomImage);
            userName = itemView.findViewById(R.id.chatRoomName);
        }
    }

}
