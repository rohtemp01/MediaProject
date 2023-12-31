package com.example.mp_app.Tools.ViewPager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mp_app.R;
import com.example.mp_app.Tools.RecyclerView.RecyclerAdapter;

public class Fragment_1_Chat_v2 extends Fragment {
    RecyclerView recyclerViewUI;
    RecyclerAdapter recyclerAdapter;
    ImageButton sendBtn;
    SharedPreferences sharedPref;
    int myFID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_chatting, container, false);
        sharedPref = getContext().getSharedPreferences("My_Data",Context.MODE_PRIVATE);
        myFID = sharedPref.getInt("FID",-1);
        recyclerViewUI = view.findViewById(R.id.recyclerViewChatting);
        Context context = getActivity();

        recyclerAdapter = new RecyclerAdapter(context);
        recyclerViewUI.setAdapter(recyclerAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerViewUI.setLayoutManager(linearLayoutManager);

        sendBtn = view.findViewById(R.id.sendImgBtn);
        sendBtn.setOnClickListener(e->{
            EditText editText = view.findViewById(R.id.editTextChat);
            String chat = editText.getText().toString();
            editText.setText("");
            recyclerAdapter.send(chat, myFID);
        });

        return view;
    }
}