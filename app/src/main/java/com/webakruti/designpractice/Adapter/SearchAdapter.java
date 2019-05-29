package com.webakruti.designpractice.Adapter;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import com.webakruti.designpractice.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private Context context;
    private List<String> colony;
    private int lastPosition = -1;
    private final static int FADE_DURATION = 1000;
    int delayAnimate = 300; //global variable

    public SearchAdapter(Context context, List<String> colony) {
        this.context = context;
        this.colony = colony;

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

        viewHolder.textViewSearch.setText(colony.get(position));

        /*//final Student.Studentbatch studentbatch = list.get(position);

        viewHolder.textViewBatchCourseName.setText(size[position]);
        viewHolder.textViewBatchTime.setText(studentbatch.getBatch().getStartTime());
        viewHolder.textViewCourseTeacher.setText(studentbatch.getWhoAssinged());

        viewHolder.textViewBatchCourseDuration.setText(studentbatch.getBatch().getCourse().getDuration());
        viewHolder.textViewBatchStartDate.setText(studentbatch.getBatch().getStartDate());
        viewHolder.textViewBatchEndDate.setText(studentbatch.getBatch().getEndDate());*/

        // Here you apply the animation when the view is bound
        //setAnimation(viewHolder.itemView, position);
        //setScaleAnimation(viewHolder.itemView);
        //setFadeAnimation(viewHolder.itemView);

        /*if(position >lastPosition) {

            Animation animation = AnimationUtils.loadAnimation(context,
                    R.anim.recycler_item);
            viewHolder.itemView.startAnimation(animation);
            lastPosition = position;
        }*/

        //setAnimation(viewHolder.itemView);

        /*Animation animation = AnimationUtils.loadAnimation(context, (position > lastPosition) ? R.anim.go_up : R.anim.go_down);
        viewHolder.itemView.startAnimation(animation);
        lastPosition = position;*/

    }

    private void setAnimation(final View view) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_out_right);
                if(view!=null){
                    view.startAnimation(animation);
                    view.setVisibility(View.VISIBLE);
                }
            }
        }, delayAnimate);
        delayAnimate+=300;
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
        return colony.size();
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
        colony = new ArrayList<>();
        colony.addAll(newList);
        notifyDataSetChanged();

    }
}
