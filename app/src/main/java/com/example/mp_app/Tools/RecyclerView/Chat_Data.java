package com.example.mp_app.Tools.RecyclerView;

public class Chat_Data {
    public Chat_Data(String chat){
        message = chat;
    }
    public String user_num;
    public String message;
    public int chatFID; //fireBase unique FID
    public boolean focused = false;
}
