package com.example.manng.csc_25_07_navigationdrawerlayout2.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manng.csc_25_07_navigationdrawerlayout2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MaGiamGiaFragment extends Fragment {


    public MaGiamGiaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ma_giam_gia, container, false);
    }

}
