package com.example.mp_app.Tools.RecyclerView;

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
    final static int MY_MESSAGE = 0;
    final static int YOUR_MESSAGE = 1;
    final static int FOCUSED_MESSAGE = 2;
    ArrayList<String> recyclerData = new ArrayList<>();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //1. viewType 따라서 viewHolder 생성
        View view;
        switch(viewType){
            case MY_MESSAGE:{
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.aaaa, parent, false);
            }
            case YOUR_MESSAGE:{
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.aaaa, parent, false);
            }
            default: return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //2. 1에서 만든 holder 받고, position에 대응되는 데이터 꽂기

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
