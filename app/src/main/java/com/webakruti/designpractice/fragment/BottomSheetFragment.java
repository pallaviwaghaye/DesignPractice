package com.webakruti.designpractice.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.webakruti.designpractice.Adapter.BottomSheetAdapter;
import com.webakruti.designpractice.EditProfileActivity;
import com.webakruti.designpractice.MainActivity;
import com.webakruti.designpractice.R;


public class BottomSheetFragment extends BottomSheetDialogFragment implements View.OnClickListener{
    public BottomSheetFragment() {
        // Required empty public constructor
    }

    private View rootView;
    private LinearLayout linearLayoutEnterPincode;
    private LinearLayout linearLayoutCurrentLocation;
    private LinearLayout linearLayoutHorizontalView;
    private RecyclerView recyclerViewBottomSheet;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);
        linearLayoutEnterPincode = (LinearLayout)rootView.findViewById(R.id.linearLayoutEnterPincode);
        linearLayoutCurrentLocation = (LinearLayout)rootView.findViewById(R.id.linearLayoutCurrentLocation);
        linearLayoutHorizontalView = (LinearLayout)rootView.findViewById(R.id.linearLayoutHorizontalView);
        recyclerViewBottomSheet = (RecyclerView)rootView.findViewById(R.id.recyclerViewBottomSheet);

        linearLayoutEnterPincode.setOnClickListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewBottomSheet.setLayoutManager(layoutManager);
        recyclerViewBottomSheet.setAdapter(new BottomSheetAdapter(getActivity(), 5));


        return  rootView;
    }


    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.linearLayoutEnterPincode :
                BottomSheetPincode bottomSheetPincode = new BottomSheetPincode();
                bottomSheetPincode.show(getActivity().getSupportFragmentManager(), bottomSheetPincode.getTag());
                /*Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
*/
                break;
            case R.id.linearLayoutCurrentLocation :
                break;
            case R.id.linearLayoutHorizontalView :
                break;
            case R.id.recyclerViewBottomSheet :


                break;
        }
    }
}
