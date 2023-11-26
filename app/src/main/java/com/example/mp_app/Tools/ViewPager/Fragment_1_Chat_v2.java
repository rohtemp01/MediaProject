package com.example.mp_app.Tools.ViewPager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mp_app.R;
import com.example.mp_app.Tools.RecyclerView.RecyclerAdapter;

public class Fragment_1_Chat_v2 extends Fragment {
    RecyclerView recyclerViewUI;
    RecyclerAdapter recyclerAdapter;
    SharedPreferences sharedPref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_chatting, container, false);
        recyclerViewUI = view.findViewById(R.id.recyclerViewChatting);
        Context context = getActivity();
        recyclerAdapter = new RecyclerAdapter(context);
        recyclerViewUI.setAdapter(recyclerAdapter);



        return view;
    }
}