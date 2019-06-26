package com.webakruti.designpractice.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.webakruti.designpractice.R;


public class FirstFragment extends Fragment {

    private View rootView;
    private TextView textViewGoTo2ndfragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_first, container, false);

        textViewGoTo2ndfragment = (TextView)rootView.findViewById(R.id.textViewGoTo2ndfragment);
        textViewGoTo2ndfragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //textViewGoTo2ndfragment.setVisibility(View.GONE);
                String abc = "Pallavi";
                SecondFragment secondFragment = new SecondFragment();
                Bundle bundle = new Bundle();
                bundle.putString("test",abc);
                secondFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.home_container,secondFragment).commit();
            }
        });



        return rootView;
    }


}
