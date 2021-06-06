package com.aranhid.roomdatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductsAdapter extends BaseAdapter {
    ArrayList<Product> products;

    public ProductsAdapter(ArrayList<Product> products) {
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return products.get(position)._id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Product product = products.get(position);
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ImageView picture = convertView.findViewById(R.id.picture);
        Picasso.get().load(product.pictureUrl).into(picture);
        TextView name = convertView.findViewById(R.id.name);
        name.setText(product.name);
        return convertView;
    }
}
