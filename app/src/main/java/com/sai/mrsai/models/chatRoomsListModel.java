package com.sai.mrsai.models;

import java.util.ArrayList;

public class chatRoomsListModel {
    ArrayList<String> chatRooms;

    public chatRoomsListModel() {
    }

    public chatRoomsListModel(ArrayList<String> chatRooms) {
        this.chatRooms = chatRooms;
    }

    public ArrayList<String> getChatRooms() {
        return chatRooms;
    }

    public void setChatRooms(ArrayList<String> chatRooms) {
        this.chatRooms = chatRooms;
    }
}
