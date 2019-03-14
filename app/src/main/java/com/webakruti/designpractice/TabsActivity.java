package com.webakruti.designpractice;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.webakruti.designpractice.fragment.FifthFragment;
import com.webakruti.designpractice.fragment.FirstFragment;
import com.webakruti.designpractice.fragment.FourthFragment;
import com.webakruti.designpractice.fragment.SecondFragment;
import com.webakruti.designpractice.fragment.ThirdFragment;

import java.util.ArrayList;
import java.util.List;

public class TabsActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    private ImageView imageViewBack;
    private SwipeRefreshLayout swipeContainer;
    private ProgressDialog progressDialogForAPI;
    //private MyRequestStatusAdapter myRequestStatusAdapter;
    boolean isCallFromPullDown = false;
    private TextView textViewNoData;
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ViewPagerAdapter adapter;
    private int typeOfComplaint;

    private int[] tabIcons = {
            R.drawable.person2,
            R.drawable.lock1,
            R.drawable.link1,
            R.drawable.mobile1,
            R.drawable.complaint_details
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        imageViewBack = (ImageView) findViewById(R.id.imageViewBack);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TabsActivity.this,FloatingActionButtonActivity.class);
                startActivity(intent);
                finish();
            }
        });

        /*setSelectedTabIndicatorGravity(INDICATOR_GRAVITY_TOP);*/

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        initViews();
        viewPager.setOnPageChangeListener(this);


        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
        tabLayout.getTabAt(4).setIcon(tabIcons[4]);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    private void initViews() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        FirstFragment firstFragment = new FirstFragment();
        SecondFragment secondFragment = new SecondFragment();
        ThirdFragment thirdFragment = new ThirdFragment();
        FourthFragment fourthFragment = new FourthFragment();
        FifthFragment fifthFragment = new FifthFragment();

        adapter.addFragment(firstFragment, "FIRST");
        adapter.addFragment(secondFragment, "SECOND");
        adapter.addFragment(thirdFragment, "THIRD");
        adapter.addFragment(fourthFragment, "FOURTH");
        adapter.addFragment(fifthFragment, "FIFTH");


        viewPager.setAdapter(adapter);


    }



    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        /*tabLayout.getTabAt(position).select();

        TabLayout.Tab tab = tabLayout.getTabAt(position);
        tab.select();

        tabLayout.setScrollPosition(position,0f,true);
        viewPager.setCurrentItem(position);*/


    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            //if want to set text into tabs
            return mFragmentTitleList.get(position);
            //if dont want to set text and want to set only icons for tabs
            //return null;
        }
    }





}
