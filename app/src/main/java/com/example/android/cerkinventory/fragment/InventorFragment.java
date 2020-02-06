package com.example.android.cerkinventory.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.cerkinventory.R;

public class InventorFragment extends Fragment implements View.OnClickListener {


    private View rootView;
    private RecyclerView recyclerView;

    public InventorFragment() {
        //required empty constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.inventor_fragment_layout, container, false);

        initView();

        return rootView;
    }

    private void initView() {
        recyclerView = rootView.findViewById(R.id.inventory_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        /*
        adapter = new HistoryAdapter(HistoryFragment.this, HistoryDAOImpl.getAllHistorys());
        recyclerView.setAdapter(adapter); */
    }

    @Override
    public void onClick(View view) {

    }
}
