package com.example.mp_app.Tools.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mp_app.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    final static int CHAT_SELF = 1;
    final static int CHAT_SELF_FOCUSED = 2;
    final static int CHAT_RECEIVED = 9;
    final static int CHAT_RECEIVED_FOCUSED = 8;

    final int myFID;
    ArrayList<Chat_Data> recyclerData = new ArrayList<>();
    private Context context;
    public RecyclerAdapter(Context context){
        this.context = context;
        myFID = context.getSharedPreferences("FID", Context.MODE_PRIVATE).getInt("MY_FID",-1);

        //System.out.println(FID);

        for(int i =0; i <2;i++){
            Chat_Data chatData = new Chat_Data();
            chatData.chatFID = -1;
            chatData.focused = false;
            chatData.message = "Nice to meet you!";
            recyclerData.add(chatData);
            for(int j =0; j < 2 ;j++){
                Chat_Data chatData2 = new Chat_Data();
                chatData2.chatFID = 777;
                chatData2.focused = false;
                chatData2.message = "today is a good day";
                recyclerData.add(chatData2);
            }
        }
        System.out.println("~~~data size: " + recyclerData.size());
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //1. viewType 따라서 viewHolder 생성
        View view;

        switch(viewType){
            case CHAT_SELF:{
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chatbox_self, parent, false);
                return new ViewHolder(view);
            }
            case CHAT_SELF_FOCUSED:{

            }
            case CHAT_RECEIVED:{
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chatbox_received, parent, false);
                return new ViewHolder(view);
            }
            case CHAT_RECEIVED_FOCUSED:{

            }
            default: return null;
        }
    }
    @Override
    public int getItemViewType(int position){
        Chat_Data chatData = recyclerData.get(position);
        if(chatData.chatFID == myFID){
            if(chatData.focused){
                return CHAT_SELF_FOCUSED;
            }
            else{
                return CHAT_SELF;
            }
        }
        else{
            if(chatData.focused){
                return CHAT_RECEIVED_FOCUSED;
            }
            else{
                return CHAT_RECEIVED;
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //2. 1에서 만든 holder 받고, position에 대응되는 데이터 꽂기
        holder.textViewMyChat.setText(recyclerData.get(position).message);
    }

    @Override
    public int getItemCount() {
        return recyclerData.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewMyChat;
        TextView textViewTime;
        TextView textViewDivideLine;
        ImageView imageProfile;
        String profileUrl;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewMyChat = itemView.findViewById(R.id.textViewMyChat);
//            textViewTime = itemView.findViewById(R.id.textViewTime);
//            textViewDivideLine = itemView.findViewById(R.id.textViewDivideLine);
//            imageProfile = itemView.findViewById(R.id.imageProfile);
        }
    }
}
