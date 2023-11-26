package com.example.mp_app.Tools.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.mp_app.R;

import java.util.ArrayList;

public class Fragment_2_Dictionary extends Fragment {
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    ListView listView;
    public Fragment_2_Dictionary() {
        arrayList.add("기분");
        arrayList.add("날씨");
        arrayList.add("강아지");


        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_dictionary,container,false);
        listView = view.findViewById(R.id.listView);

        arrayAdapter = new ArrayAdapter<String>(view.getContext(),R.layout.list_view_item, R.id.text_view_left, arrayList);
        listView.setAdapter(arrayAdapter);
        return inflater.inflate(R.layout.frag_dictionary, container, false);
    }
}