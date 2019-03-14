package com.webakruti.designpractice.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.webakruti.designpractice.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private Context context;
    private List<String> stations;
    private int lastPosition = -1;
    private final static int FADE_DURATION = 1000;

    public SearchAdapter(Context context, List<String> stations) {
        this.context = context;
        this.stations = stations;

    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemsearch, viewGroup, false);
        SearchAdapter.ViewHolder viewHolder = new SearchAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder viewHolder, final int position) {

        viewHolder.textViewSearch.setText(stations.get(position));

        /*//final Student.Studentbatch studentbatch = list.get(position);

        viewHolder.textViewBatchCourseName.setText(size[position]);
        viewHolder.textViewBatchTime.setText(studentbatch.getBatch().getStartTime());
        viewHolder.textViewCourseTeacher.setText(studentbatch.getWhoAssinged());

        viewHolder.textViewBatchCourseDuration.setText(studentbatch.getBatch().getCourse().getDuration());
        viewHolder.textViewBatchStartDate.setText(studentbatch.getBatch().getStartDate());
        viewHolder.textViewBatchEndDate.setText(studentbatch.getBatch().getEndDate());*/

        // Here you apply the animation when the view is bound
        //setAnimation(viewHolder.itemView, position);
        setScaleAnimation(viewHolder.itemView);
        //setFadeAnimation(viewHolder.itemView);

    }

    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            //Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            ScaleAnimation animation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(new Random().nextInt(501));//to make duration random number between [0,501)
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }

    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }


    @Override
    public int getItemCount() {
        return stations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewSearch;


        public ViewHolder(View itemView) {
            super(itemView);

            textViewSearch = (TextView)itemView.findViewById(R.id.textViewSearch);
        }
    }

    public void updateList(List<String> newList)
    {
        stations = new ArrayList<>();
        stations.addAll(newList);
        notifyDataSetChanged();

    }
}
