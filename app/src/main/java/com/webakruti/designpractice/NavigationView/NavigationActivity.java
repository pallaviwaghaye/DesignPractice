package com.webakruti.designpractice.NavigationView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.webakruti.designpractice.MainActivity;
import com.webakruti.designpractice.R;
import com.webakruti.designpractice.SocialLogin.LoginActivity;
import com.webakruti.designpractice.fragment.FirstFragment;
import com.webakruti.designpractice.fragment.HomeFragment;
import com.webakruti.designpractice.fragment.SecondFragment;
import com.webakruti.designpractice.fragment.ThirdFragment;

public class NavigationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView,settingsNavigationView;
    private FragmentManager fragManager;
    private TextView toolbarUserDetailsHomeTitle;
    private DoubleDrawerView doubleDrawerView;

    private TextView textViewFName;
    private TextView textViewLName;
    private TextView textViewMobileNo;
    private ImageView imageViewNavUser;
    private ImageView imageViewDownArrow;
    private Spinner spinnerNavDrawerList;


    /*ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    List<MenuModel> headerList = new ArrayList<>();
    HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbarUserDetailsHomeTitle = (TextView) findViewById(R.id.toolbarUserDetailsHomeTitle);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigationView);

        doubleDrawerView = (DoubleDrawerView) findViewById(R.id.double_drawer_view);
        settingsNavigationView = (NavigationView) findViewById(R.id.settings_navigation_view);

        navigationView.setNavigationItemSelectedListener(this);
        settingsNavigationView.setNavigationItemSelectedListener(this);

        //drawerLayout.openDrawer(Gravity.LEFT);

        navigationView.getMenu().getItem(5).setActionView(R.layout.menu_image);
        /*navigationView.getMenu().getItem(1).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });*/


        /*expandableListView = findViewById(R.id.expandableListView);
        prepareMenuData();
        populateExpandableList();*/

        //this will shows original color of menu item icons
        navigationView.setItemIconTintList(null);

        View headerLayout = navigationView.getHeaderView(0);

        textViewFName = (TextView) headerLayout.findViewById(R.id.textViewFName);
        textViewLName = (TextView) headerLayout.findViewById(R.id.textViewLName);
        textViewMobileNo = (TextView) headerLayout.findViewById(R.id.textViewMobileNo);
        imageViewNavUser = (ImageView) headerLayout.findViewById(R.id.imageViewNavUser);
        imageViewDownArrow = (ImageView) headerLayout.findViewById(R.id.imageViewDownArrow);
        spinnerNavDrawerList = (Spinner) headerLayout.findViewById(R.id.spinnerNavDrawerList);

        Menu menu = navigationView.getMenu();

        MenuItem navigationLogout = menu.findItem(R.id.navigationLogout);

        imageViewDownArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(NavigationActivity.this, MainActivity.class);
               startActivity(intent);
               finish();
            }
        });

        String[] list = getResources().getStringArray(R.array.colony);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,list);
        spinnerNavDrawerList.setAdapter(adapter);




        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        fragManager = getSupportFragmentManager();
        fragManager.beginTransaction().replace(R.id.home_container, new HomeFragment()).commit();

    }

    /*public void OnClickArrow(View v){

        *//*navigationView.getMenu().clear();
        navigationView.inflateMenu(R.menu.dotmenu);*//*
        Intent intent = new Intent(NavigationActivity.this,EditProfileActivity.class);
        startActivity(intent);
        finish();
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /*private void prepareMenuData() {

        MenuModel menuModel = new MenuModel(navigationView.getMenu().getItem(0).toString(), true, false, ""); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        *//*menuModel = new MenuModel(navigationView.getMenu().getItem(2).toString(), false, false, ""); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        menuModel = new MenuModel(navigationView.getMenu().getItem(3).toString(), false, false, ""); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        menuModel = new MenuModel(navigationView.getMenu().getItem(4).toString(), false, false, ""); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        menuModel = new MenuModel(navigationView.getMenu().getItem(5).toString(), false, false, ""); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        menuModel = new MenuModel(navigationView.getMenu().getItem(6).toString(), false, false, ""); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        menuModel = new MenuModel(navigationView.getMenu().getItem(7).toString(), false, false, ""); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        menuModel = new MenuModel(navigationView.getMenu().getItem(8).toString(), false, false, ""); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        menuModel = new MenuModel(navigationView.getMenu().getItem(9).toString(), false, false, ""); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        menuModel = new MenuModel(navigationView.getMenu().getItem(10).toString(), false, false, ""); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        menuModel = new MenuModel(navigationView.getMenu().getItem(11).toString(), false, false, ""); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        menuModel = new MenuModel(navigationView.getMenu().getItem(12).toString(), false, false, ""); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);*//*

        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }

        menuModel = new MenuModel(navigationView.getMenu().getItem(1).toString(), true, true, ""); //Menu of Java Tutorials
        headerList.add(menuModel);
        List<MenuModel> childModelsList = new ArrayList<>();
        MenuModel childModel = new MenuModel("Orders", false, false, "");
        childModelsList.add(childModel);

        childModel = new MenuModel("Wishlist", false, false, "");
        childModelsList.add(childModel);

        childModel = new MenuModel("Addresses", false, false, "");
        childModelsList.add(childModel);



        if (menuModel.hasChildren) {
            Log.d("API123","here");
            childList.put(menuModel, childModelsList);
        }

        *//*childModelsList = new ArrayList<>();
        menuModel = new MenuModel("Python Tutorials", true, true, ""); //Menu of Python Tutorials
        headerList.add(menuModel);
        childModel = new MenuModel("Python AST – Abstract Syntax Tree", false, false, "https://www.journaldev.com/19243/python-ast-abstract-syntax-tree");
        childModelsList.add(childModel);

        childModel = new MenuModel("Python Fractions", false, false, "https://www.journaldev.com/19226/python-fractions");
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }*//*
    }*/

    /*private void populateExpandableList() {

        expandableListAdapter = new ExpandableListAdapter(this, headerList, childList);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                if (headerList.get(groupPosition).isGroup) {
                    *//*if (!headerList.get(groupPosition).hasChildren) {
                        WebView webView = findViewById(R.id.webView);
                        webView.loadUrl(headerList.get(groupPosition).url);
                        onBackPressed();
                    }*//*
                }

                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                if (childList.get(headerList.get(groupPosition)) != null) {
                    MenuModel model = childList.get(headerList.get(groupPosition)).get(childPosition);

                    *//*if (model.url.length() > 0) {
                        WebView webView = findViewById(R.id.webView);
                        webView.loadUrl(model.url);
                        onBackPressed();
                    }*//*
                }

                return false;
            }
        });
    }*/


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        menuItem.setChecked(true);

        switch (menuItem.getItemId()) {

            case R.id.navigationHome:
                toolbarUserDetailsHomeTitle.setText("Home");
                // toolbarStudentDetailsHomeTitle.setText("My details");
                // SwachhataKendraFragment fragment = new SwachhataKendraFragment();
                fragManager.beginTransaction().replace(R.id.home_container, new HomeFragment()).commit();
                drawerLayout.closeDrawers();
                break;

            case R.id.navigationMyProfile:
                toolbarUserDetailsHomeTitle.setText("My Profile");
                // toolbarStudentDetailsHomeTitle.setText("My details");
                // SwachhataKendraFragment fragment = new SwachhataKendraFragment();
                fragManager.beginTransaction().replace(R.id.home_container, new FirstFragment()).commit();
                drawerLayout.closeDrawers();
                break;

            case R.id.navigationSettings:
                doubleDrawerView.openInnerDrawer();
                break;

            case R.id.menu_close_settings:
                doubleDrawerView.closeInnerDrawer();
                break;

            case R.id.menu_setting_1:

                toolbarUserDetailsHomeTitle.setText("Second Fragment");
                fragManager.beginTransaction().replace(R.id.home_container, new SecondFragment()).commit();
                drawerLayout.closeDrawers();
                break;

            case R.id.menu_setting_2:
                AlertDialog.Builder alertDialog1 = new AlertDialog.Builder(NavigationActivity.this);
                // Setting Dialog Title
                alertDialog1.setTitle("Third Fragment");
                // Setting Dialog Message
                alertDialog1.setMessage("Are you sure to go?");
                // Setting Icon to Dialog
                // Setting Positive "Yes" Button
                alertDialog1.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                toolbarUserDetailsHomeTitle.setText("Third Fragment");
                fragManager.beginTransaction().replace(R.id.home_container, new ThirdFragment()).commit();
                drawerLayout.closeDrawers();

                    }
                });
                // Setting Negative "NO" Button
                alertDialog1.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                // Showing Alert Message
                alertDialog1.show();
                break;

            case R.id.navigationLogout:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(NavigationActivity.this);
                // Setting Dialog Title
                alertDialog.setTitle("Logout");
                // Setting Dialog Message
                alertDialog.setMessage("Are you sure to logout?");
                // Setting Icon to Dialog
                // Setting Positive "Yes" Button
                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //  SharedPreferenceManager.clearPreferences();
                        Intent intent = new Intent(NavigationActivity.this, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                });
                // Setting Negative "NO" Button
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                // Showing Alert Message
                alertDialog.show();


                break;
        }

        return true;
    }
}
