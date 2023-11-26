package com.example.mp_app.Tools.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.mp_app.R;
import com.example.mp_app.util.TranslateUtil;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_dictionary, container, false);
        listView = view.findViewById(R.id.listView);

        arrayAdapter = new ArrayAdapter<String>(view.getContext(), R.layout.list_view_item, R.id.text_view_left, arrayList);
        listView.setAdapter(arrayAdapter);

        // ListView 아이템 클릭 이벤트 처리
        listView.setOnItemClickListener((parent, view1, position, id) -> {
            String selectedWord = arrayList.get(position);
            String translatedWord = TranslateUtil.translateText(selectedWord, "en");

            // 번역된 결과를 리스트에 추가
            arrayList.add(translatedWord);
            arrayAdapter.notifyDataSetChanged();
        });

        return view;
    }

}