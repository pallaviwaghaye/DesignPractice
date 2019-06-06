package com.webakruti.designpractice.NavigationView;

/**
 * Created by anupamchugh on 22/12/17.
 */

public class MenuModel {

    public String menuName, url;
    public boolean hasChildren, isGroup;
    public int img;

    public MenuModel(String menuName, boolean isGroup, boolean hasChildren, String url, int img) {

        this.menuName = menuName;
        this.url = url;
        this.isGroup = isGroup;
        this.hasChildren = hasChildren;
        this.img = img;
    }
}
