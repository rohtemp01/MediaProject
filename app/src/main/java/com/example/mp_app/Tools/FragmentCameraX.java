package com.example.mp_app.Tools;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mp_app.R;

public class FragmentCameraX extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_camera_x, container, false);
        //TextView textView = view.findViewById(R.id.textView2);
        return view;
    }
}