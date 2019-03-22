package com.webakruti.designpractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.SearchView;

import com.webakruti.designpractice.adapter.SearchAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchListViewActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private ImageView imageViewBack;

    private List<String> colony = new ArrayList<>();
    private SearchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list_view);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewSearch);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imageViewBack = (ImageView)findViewById(R.id.imageViewBack);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchListViewActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //recycler view using array of string from strings.xml
        colony = Arrays.asList(getResources().getStringArray(R.array.colony));
        /*LinearLayoutManager layoutManager = new LinearLayoutManager(SearchListViewActivity.this, LinearLayoutManager.VERTICAL, false);
        adapter = new SearchAdapter(SearchListViewActivity.this,stations);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);*/

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager1);
        recyclerView.setHasFixedSize(true);

        AnimationSet set = new AnimationSet(true);

        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(500);
        set.addAnimation(animation);

        animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF, 0.0f
        );
        animation.setDuration(500);
        set.addAnimation(animation);

        LayoutAnimationController controller = new LayoutAnimationController(set, 0.5f);


        adapter = new SearchAdapter(this,colony);

        recyclerView.setAdapter(adapter);
        //adapter = new RecycleViewAdapter(poetNameSetGets, this);
        recyclerView.setLayoutAnimation(controller);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbarmenu,menu);
        //getMenuInflater().inflate(R.menu.toolbarmenu,menu);

        /*MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);*/
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        String userInput = newText.toLowerCase();
        List<String> newList = new ArrayList<>();
        for(String newColony: colony)
        {
            if(newColony.toLowerCase().contains(userInput))
            {
                newList.add(newColony);
            }
        }

        adapter.updateList(newList);

        return true;

        //return false;
    }
}
