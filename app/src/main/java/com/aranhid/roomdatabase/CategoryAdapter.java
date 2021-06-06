package com.aranhid.roomdatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryAdapter extends BaseAdapter {
    ArrayList<Category> categories;

    public CategoryAdapter(ArrayList<Category> categories) {
        this.categories = categories;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return categories.get(position)._id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Category category = categories.get(position);
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ImageView picture = convertView.findViewById(R.id.picture);
        Picasso.get().load(category.pictureUrl).into(picture);
        TextView name = convertView.findViewById(R.id.name);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (category._id == 1)
                {
                    ((MainActivity)parent.getContext()).getProducts();
                } else {
                    ((MainActivity)parent.getContext()).getProductsByCategory(category._id);
                }
            }
        });

        name.setText(category.name);
        return convertView;
    }

}
