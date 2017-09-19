package com.anandniketan.skool360teacher.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.anandniketan.skool360teacher.Models.MenuoptionItemModel;
import com.anandniketan.skool360teacher.R;

import java.util.ArrayList;

/**
 * Created by admsandroid on 9/15/2017.
 */

public class MenuoptionItemAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<MenuoptionItemModel> menuOptionItems;

    public MenuoptionItemAdapter(Context context, ArrayList<MenuoptionItemModel> menuOptionItems){
        this.context = context;
        this.menuOptionItems = menuOptionItems;
    }
    @Override
    public int getCount() {
        return menuOptionItems.size();
    }

    @Override
    public Object getItem(int position) {
        return menuOptionItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.menu_drawer_item, null);
        }

        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
        txtTitle.setText(menuOptionItems.get(position).getName());
        return convertView;
    }

}
