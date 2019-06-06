package com.webakruti.designpractice.NavigationView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.webakruti.designpractice.R;
import com.webakruti.designpractice.SocialLogin.LoginActivity;
import com.webakruti.designpractice.fragment.FifthFragment;
import com.webakruti.designpractice.fragment.FirstFragment;
import com.webakruti.designpractice.fragment.FourthFragment;
import com.webakruti.designpractice.fragment.HomeFragment;
import com.webakruti.designpractice.fragment.SecondFragment;
import com.webakruti.designpractice.fragment.ThirdFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NavWithListViewActivity extends AppCompatActivity  {


    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    List<MenuModel> headerList = new ArrayList<>();
    HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();
    NavigationView navigationView;
    private TextView toolbarUserDetailsHomeTitle;
    private FragmentManager fragManager;
    DrawerLayout drawer;

    private ImageView imageView;
    private TextView textViewName , textViewEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_with_list_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbarUserDetailsHomeTitle = (TextView) findViewById(R.id.toolbarUserDetailsHomeTitle);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        navigationView = findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(this);

        View headerLayout = navigationView.getHeaderView(0);

        imageView = (ImageView)headerLayout.findViewById(R.id.imageView);
        textViewName = (TextView)headerLayout.findViewById(R.id.textViewName);
        textViewEmail = (TextView)headerLayout.findViewById(R.id.textViewEmail);

        Picasso.with(NavWithListViewActivity.this)
                .load(R.drawable.circularimage)
                .into(imageView);
        textViewName.setText("Android Studio");
        textViewEmail.setText("android@gmail.com");


        expandableListView = findViewById(R.id.expandableListView);
        prepareMenuData();
        populateExpandableList();


        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



        fragManager = getSupportFragmentManager();
        fragManager.beginTransaction().replace(R.id.home_container, new FirstFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

   /* @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/

    /*private void prepareMenuData() {

        MenuModel menuModel = new MenuModel("Android WebView Tutorial", true, false, ""); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }

        menuModel = new MenuModel("Java Tutorials", true, true, ""); //Menu of Java Tutorials
        headerList.add(menuModel);
        List<MenuModel> childModelsList = new ArrayList<>();
        MenuModel childModel = new MenuModel("Core Java Tutorial", false, false, "https://www.journaldev.com/7153/core-java-tutorial");
        childModelsList.add(childModel);

        childModel = new MenuModel("Java FileInputStream", false, false, "https://www.journaldev.com/19187/java-fileinputstream");
        childModelsList.add(childModel);

        childModel = new MenuModel("Java FileReader", false, false, "https://www.journaldev.com/19115/java-filereader");
        childModelsList.add(childModel);


        if (menuModel.hasChildren) {
            Log.d("API123","here");
            childList.put(menuModel, childModelsList);
        }

        childModelsList = new ArrayList<>();
        menuModel = new MenuModel("Python Tutorials", true, true, ""); //Menu of Python Tutorials
        headerList.add(menuModel);
        childModel = new MenuModel("Python AST – Abstract Syntax Tree", false, false, "https://www.journaldev.com/19243/python-ast-abstract-syntax-tree");
        childModelsList.add(childModel);

        childModel = new MenuModel("Python Fractions", false, false, "https://www.journaldev.com/19226/python-fractions");
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }
    }*/


    private void prepareMenuData() {

        MenuModel menuModel = new MenuModel("Home", false, false, "",0); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        menuModel = new MenuModel("My Profile", false, true, "",R.drawable.downarrow3); //Menu of Java Tutorials
        headerList.add(menuModel);
        List<MenuModel> childModelsList = new ArrayList<>();
        MenuModel childModel = new MenuModel("Orders", false, false, "",0);
        childModelsList.add(childModel);

        childModel = new MenuModel("Wishlist", false, false, "",0);
        childModelsList.add(childModel);

        childModel = new MenuModel("Addresses", false, false, "",0);
        childModelsList.add(childModel);
        if (menuModel.hasChildren) {
            Log.d("API123", "here");
            childList.put(menuModel, childModelsList);
        }

        menuModel = new MenuModel("My Enquiry", false, false, "",0); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        menuModel = new MenuModel("Support", false, false, "",0); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        menuModel = new MenuModel("Logout", false, false, "",0); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        menuModel = new MenuModel("Settings", false, true, "",R.drawable.downarrow3); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);
        childModelsList = new ArrayList<>();
        childModel = new MenuModel("Settings1", false, false, "",0);
        childModelsList.add(childModel);

        childModel = new MenuModel("Settings2", false, false, "",0);
        childModelsList.add(childModel);

        childModel = new MenuModel("Settings3", false, false, "",0);
        childModelsList.add(childModel);


        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);

        }

        menuModel = new MenuModel("Help", false, false, "",0); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        menuModel = new MenuModel("Help", false, false, "",0); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);
        menuModel = new MenuModel("Help", false, false, "",0); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);
        menuModel = new MenuModel("Help", false, false, "",0); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);
        menuModel = new MenuModel("Help", false, false, "",0); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);
        menuModel = new MenuModel("Help", false, false, "",0); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);
        menuModel = new MenuModel("Help", false, false, "",0); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);
        menuModel = new MenuModel("Help", false, false, "",0); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);
        menuModel = new MenuModel("Help", false, false, "",0); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);
        menuModel = new MenuModel("Help", false, false, "",0); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);


        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }

        /*if(childModelsList != null) {
            Picasso.with(NavWithListViewActivity.this)
                    .load(R.drawable.downarrow3)
                    .into(menuModel.img);
        }*/


    }

    private void populateExpandableList() {

        expandableListAdapter = new ExpandableListAdapter(this, headerList, childList);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                if (headerList.get(groupPosition).isGroup) {
                    /*if (!headerList.get(groupPosition).hasChildren) {
                        WebView webView = findViewById(R.id.webView);
                        webView.loadUrl(headerList.get(groupPosition).url);
                        onBackPressed();
                    }*/
                }

                if(groupPosition == 0)
                {
                    toolbarUserDetailsHomeTitle.setText("Home");
                    fragManager.beginTransaction().replace(R.id.home_container, new FirstFragment()).commit();
                    drawer.closeDrawers();
                }
                if(groupPosition == 2)
                {
                    toolbarUserDetailsHomeTitle.setText("My Enquiry");
                    fragManager.beginTransaction().replace(R.id.home_container, new FirstFragment()).commit();
                    drawer.closeDrawers();
                }
                if(groupPosition == 3)
                {
                    toolbarUserDetailsHomeTitle.setText("Support");
                    fragManager.beginTransaction().replace(R.id.home_container, new FirstFragment()).commit();
                    drawer.closeDrawers();
                }
                if(groupPosition == 4)
                {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(NavWithListViewActivity.this);
                    alertDialog.setTitle("Logout");
                    alertDialog.setMessage("Are you sure to logout?");
                    alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(NavWithListViewActivity.this, LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }
                    });
                    alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    alertDialog.show();
                }
                if(groupPosition == 6)
                {
                    toolbarUserDetailsHomeTitle.setText("Help");
                    fragManager.beginTransaction().replace(R.id.home_container, new FirstFragment()).commit();
                    drawer.closeDrawers();
                }


                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                if (childList.get(headerList.get(groupPosition)) != null) {
                    MenuModel model = childList.get(headerList.get(groupPosition)).get(childPosition);

                    if(groupPosition == 1) {
                        if (childPosition == 0) {
                            toolbarUserDetailsHomeTitle.setText("Orders");
                            fragManager.beginTransaction().replace(R.id.home_container, new FirstFragment()).commit();
                            drawer.closeDrawers();
                        }
                        if (childPosition == 1) {
                            toolbarUserDetailsHomeTitle.setText("Wishlist");
                            fragManager.beginTransaction().replace(R.id.home_container, new SecondFragment()).commit();
                            drawer.closeDrawers();
                        }

                        if (childPosition == 2) {
                            toolbarUserDetailsHomeTitle.setText("Addresses");
                            fragManager.beginTransaction().replace(R.id.home_container, new ThirdFragment()).commit();
                            drawer.closeDrawers();
                        }
                    }
                    if(groupPosition == 5) {

                        if (childPosition == 0) {
                            toolbarUserDetailsHomeTitle.setText("Setting1");
                            fragManager.beginTransaction().replace(R.id.home_container, new FourthFragment()).commit();
                            drawer.closeDrawers();
                        }

                        if (childPosition == 1) {
                            toolbarUserDetailsHomeTitle.setText("Setting2");
                            fragManager.beginTransaction().replace(R.id.home_container, new FifthFragment()).commit();
                            drawer.closeDrawers();
                        }

                        if (childPosition == 2) {
                            toolbarUserDetailsHomeTitle.setText("Setting3");
                            fragManager.beginTransaction().replace(R.id.home_container, new FifthFragment()).commit();
                            drawer.closeDrawers();
                        }
                    }


                    /*if (model.url.length() > 0) {
                        WebView webView = findViewById(R.id.webView);
                        webView.loadUrl(model.url);
                        onBackPressed();
                    }*/
                }

                return false;
            }
        });
    }

    /*@Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }*/
}
