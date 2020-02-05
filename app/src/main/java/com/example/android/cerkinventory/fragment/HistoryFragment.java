package com.example.android.cerkinventory.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.android.cerkinventory.R;

public class HistoryFragment extends Fragment implements View.OnClickListener {

    private View rootView;

    public HistoryFragment() {
        // required empty constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.history_fragment_layout, container, false);


        return rootView;
    }


    @Override
    public void onClick(View view) {

    }
}
