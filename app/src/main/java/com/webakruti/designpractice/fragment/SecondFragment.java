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

import org.w3c.dom.Text;

public class SecondFragment extends Fragment {

    private View rootView;
    private TextView textViewTest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_second, container, false);

        textViewTest = (TextView)rootView.findViewById(R.id.textViewTest);
        String value = getArguments().getString("test");
        textViewTest.setText(value);

        return rootView;
    }


}
