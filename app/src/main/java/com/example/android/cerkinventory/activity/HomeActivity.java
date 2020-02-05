package com.example.android.cerkinventory.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;


import com.example.android.cerkinventory.R;
import com.example.android.cerkinventory.fragment.HistoryFragment;

import com.example.android.cerkinventory.fragment.InventorFragment;

public class HomeActivity extends AppCompatActivity {


    private Fragment activeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        handleBottomNavigation();

    }

    private void handleBottomNavigation() {
        View inventor = findViewById(R.id.inventor);

        View history = findViewById(R.id.history);

        initBottomView(0);

        // lamba expression
        inventor.setOnClickListener(view -> initBottomView(0));
        history.setOnClickListener(view -> initBottomView(1));

    }



    private void initBottomView(int position) {
        View inventorSep = findViewById(R.id.inventorSeparator);
        View historySep = findViewById(R.id.historySeparator);


        // condition ternaire if(possition == 0) {} else {}
        inventorSep.setVisibility(position == 0 ? View.VISIBLE : View.GONE);
        historySep.setVisibility(position == 1 ? View.VISIBLE : View.GONE);


        activeFragment = null;
        switch (position) {
            case 0:
                activeFragment = new InventorFragment();
                break;
            case 1:
                activeFragment = new HistoryFragment();
                break;

        }

        if (activeFragment == null) {
            return;
        }


        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, activeFragment);
        ft.addToBackStack(activeFragment.getClass().getName());
        ft.commitAllowingStateLoss();
    }

    /* Pas nécéssaire à priori

    // ici on va regarder quel fragment est actif et on va alors faire un refresh du UI
    private void refreshUiAfterCloseMap() {
        if (activeFragment instanceof InventorFragment) {
            ((InventorFragment) activeFragment).refreshUI();
        } else if (activeFragment instanceof HistoryFragment) {
            ((HistoryFragment) activeFragment).refreshUI();
        }



    }*/
}