package com.webakruti.designpractice.fragment;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.webakruti.designpractice.R;

public class BottomSheetPincode extends BottomSheetDialogFragment implements View.OnClickListener{
    private View rootView;
    private Button buttonApplyPincode;
    private EditText editTextEnterPincode;
    private ImageView imageViewBack;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.bottom_sheet_enter_pincode, container, false);
        imageViewBack = (ImageView)rootView.findViewById(R.id.imageViewBack);
        editTextEnterPincode = (EditText)rootView.findViewById(R.id.editTextEnterPincode);
        buttonApplyPincode = (Button)rootView.findViewById(R.id.buttonApplyPincode);

        imageViewBack.setOnClickListener(this);


        return  rootView;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.imageViewBack :
                BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
                bottomSheetFragment.show(getActivity().getSupportFragmentManager(), bottomSheetFragment.getTag());

                break;
            case R.id.buttonApplyPincode :
                break;
        }
    }
}
