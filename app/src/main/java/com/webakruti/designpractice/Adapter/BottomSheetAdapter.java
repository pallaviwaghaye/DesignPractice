package com.webakruti.designpractice.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.webakruti.designpractice.R;

public class BottomSheetAdapter extends RecyclerView.Adapter<BottomSheetAdapter.ViewHolder> {

    private Context context;
    private int size;

    public BottomSheetAdapter(Context context, int size) {
        this.context = context;
        this.size = size;

    }

    @NonNull
    @Override
    public BottomSheetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_bottom_sheet, viewGroup, false);
        BottomSheetAdapter.ViewHolder viewHolder = new BottomSheetAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BottomSheetAdapter.ViewHolder viewHolder, final int position) {


        /*viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FoodDeliveryRestroActivity.class);
                context.startActivity(intent);

            }
        });*/


    }

    @Override
    public int getItemCount() {
        return size;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private CardView cardView;
        private TextView textViewCustomerName;
        private TextView textViewCustomerAddress;

        public ViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView)itemView.findViewById(R.id.cardView);
            textViewCustomerName = (TextView)itemView.findViewById(R.id.textViewCustomerName);
            textViewCustomerAddress = (TextView)itemView.findViewById(R.id.textViewCustomerAddress);
        }
    }
}
