package com.sai.mrsai.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sai.mrsai.R;
import com.sai.mrsai.adapters.ChatRoomAdapter;
import com.sai.mrsai.managers.SharedPreferenceManager;
import com.sai.mrsai.models.ChatRoomModel;
import com.sai.mrsai.models.UserDetails;

import java.util.ArrayList;
import java.util.List;

public class ChatsFragment extends Fragment {
    DatabaseReference chatsReference,userReference;
    UserDetails ud;
    List<Object> chatRoomsList = new ArrayList<>();
    List<ChatRoomModel> chatRoomModelList = new ArrayList<>();
    ChatRoomAdapter chatRoomsAdapter;

    public ChatsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ChatsView = inflater.inflate(R.layout.fragment_chats, container, false);
        ud = SharedPreferenceManager.getUserData(requireContext());


        userReference = FirebaseDatabase.getInstance().getReference("Users").child(ud.getUid()).child("chatRooms");
        chatsReference = FirebaseDatabase.getInstance().getReference("chatRooms");
        chatsReference.keepSynced(true);
        userReference.keepSynced(true);

        userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    chatRoomsList = (List<Object>) snapshot.getValue();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

        RecyclerView recyclerView  = ChatsView.findViewById(R.id.RecycleView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        fetchChatRooms(recyclerView);
        return ChatsView;
    }

    private void fetchChatRooms(RecyclerView recyclerView) {
        chatRoomsAdapter = new ChatRoomAdapter(getContext(),chatRoomModelList);
        recyclerView.setAdapter(chatRoomsAdapter);
        for (Object chatRoomId:chatRoomsList) {
            chatsReference.child(chatRoomId.toString()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot ds: snapshot.getChildren()) {
                        ChatRoomModel chatRoomModel = ds.getValue(ChatRoomModel.class);
                        chatRoomModelList.add(chatRoomModel);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }chatRoomsAdapter.notifyDataSetChanged();
    }
}