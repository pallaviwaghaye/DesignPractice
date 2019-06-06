package com.webakruti.designpractice.NavigationView;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.webakruti.designpractice.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by anupamchugh on 22/12/17.
 */


public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<MenuModel> listDataHeader;
    private HashMap<MenuModel, List<MenuModel>> listDataChild;
    ImageView imageViewArrow;

    public ExpandableListAdapter(Context context, List<MenuModel> listDataHeader,
                                 HashMap<MenuModel, List<MenuModel>> listChildData) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listDataChild = listChildData;
    }



    @Override
    public MenuModel getChild(int groupPosition, int childPosititon) {
        return this.listDataChild.get(this.listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = getChild(groupPosition, childPosition).menuName;

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group_child, null);
        }

        TextView txtListChild = convertView
                .findViewById(R.id.lblListItem);

        txtListChild.setText(childText);

        /*if (childPosition == this.listDataChild.get(this.listDataHeader.get(groupPosition))
                .size()) {
            convertView.setPadding(20, 0, 0, 20);
        } else

            convertView.setPadding(0, 0, 0, 0);
*/

        return convertView;

    }

    @Override
    public int getChildrenCount(int groupPosition) {

        if (this.listDataChild.get(this.listDataHeader.get(groupPosition)) == null)
            return 0;
        else
            return this.listDataChild.get(this.listDataHeader.get(groupPosition))
                    .size();
    }

    @Override
    public MenuModel getGroup(int groupPosition) {
        return this.listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.listDataHeader.size();

    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        String headerTitle = getGroup(groupPosition).menuName;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group_header, null);
        }

        /*if (isExpanded)
            convertView.setPadding(0, 0, 0, 0);
        else
            convertView.setPadding(20, 0, 0, 0);*/

        TextView lblListHeader = convertView.findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        imageViewArrow = convertView.findViewById(R.id.imageViewArrow);


        if(getGroup(groupPosition).hasChildren == true) {
            imageViewArrow.setVisibility(View.VISIBLE);


            if(isExpanded == true) {
                imageViewArrow.setImageDrawable(context.getResources().getDrawable(R.drawable.up_arrow));
            }
            if(isExpanded == false) {
                imageViewArrow.setImageDrawable(context.getResources().getDrawable(R.drawable.down_arrow3));
            }

            //imageViewArrow.setImageDrawable(context.getResources().getDrawable(R.drawable.downarrow3));
        }else if(getGroup(groupPosition).hasChildren == false){
            imageViewArrow.setVisibility(View.INVISIBLE);
            if(isExpanded == false) {
                imageViewArrow.setImageDrawable(context.getResources().getDrawable(R.drawable.down_arrow3));
            }
            if(isExpanded == true) {
                imageViewArrow.setImageDrawable(context.getResources().getDrawable(R.drawable.up_arrow));
            }
            //imageViewArrow.setImageDrawable(null);
        }else{
            imageViewArrow.setVisibility(View.INVISIBLE);
            //imageViewArrow.setImageDrawable(null);
            if(isExpanded == true) {
                imageViewArrow.setImageDrawable(context.getResources().getDrawable(R.drawable.up_arrow));
            }
            if(isExpanded == false) {
                imageViewArrow.setImageDrawable(context.getResources().getDrawable(R.drawable.down_arrow3));
            }
        }





        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}