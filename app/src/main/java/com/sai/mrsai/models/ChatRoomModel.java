package com.sai.mrsai.models;

import java.util.ArrayList;
import java.util.List;

public class ChatRoomModel {
    String chatRoomId,chatRoomName,chatRoomImage,lastMsgSenderId,chatRoomType,lastMsgTimeStamp,msg;
    ArrayList<Integer> lastMsgStatus;
    public ChatRoomModel() {
    }

    public ChatRoomModel(String chatRoomId, String chatRoomName, String chatRoomImage, String lastMsgSenderId, String chatRoomType, String lastMsgTimeStamp, String msg, ArrayList<Integer> lastMsgStatus) {
        this.chatRoomId = chatRoomId;
        this.chatRoomName = chatRoomName;
        this.chatRoomImage = chatRoomImage;
        this.lastMsgSenderId = lastMsgSenderId;
        this.chatRoomType = chatRoomType;
        this.lastMsgTimeStamp = lastMsgTimeStamp;
        this.msg = msg;
        this.lastMsgStatus = lastMsgStatus;
    }

    public String getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(String chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public String getChatRoomName() {
        return chatRoomName;
    }

    public void setChatRoomName(String chatRoomName) {
        this.chatRoomName = chatRoomName;
    }

    public String getChatRoomImage() {
        return chatRoomImage;
    }

    public void setChatRoomImage(String chatRoomImage) {
        this.chatRoomImage = chatRoomImage;
    }

    public String getLastMsgSenderId() {
        return lastMsgSenderId;
    }

    public void setLastMsgSenderId(String lastMsgSenderId) {
        this.lastMsgSenderId = lastMsgSenderId;
    }

    public String getChatRoomType() {
        return chatRoomType;
    }

    public void setChatRoomType(String chatRoomType) {
        this.chatRoomType = chatRoomType;
    }

    public String getLastMsgTimeStamp() {
        return lastMsgTimeStamp;
    }

    public void setLastMsgTimeStamp(String lastMsgTimeStamp) {
        this.lastMsgTimeStamp = lastMsgTimeStamp;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<Integer> getLastMsgStatus() {
        return lastMsgStatus;
    }

    public void setLastMsgStatus(ArrayList<Integer> lastMsgStatus) {
        this.lastMsgStatus = lastMsgStatus;
    }
}
