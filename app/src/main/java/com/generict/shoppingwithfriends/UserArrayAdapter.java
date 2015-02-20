package com.generict.shoppingwithfriends;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseUser;

import java.util.List;

public class UserArrayAdapter extends ArrayAdapter<ParseUser> {
    private final Activity context;
    private final List<ParseUser> users;

    static class ViewHolder {
        public TextView text;
        public ImageView image;
    }

    public UserArrayAdapter(Activity context, int textViewResourceId, List<ParseUser> us) {
        super((Context) context, textViewResourceId, us);
        this.context = context;
        this.users = us;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        // reuse views
        if (rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.row_layout, null);
            // configure view holder
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.text = (TextView) rowView.findViewById(R.id.label);
            viewHolder.image = (ImageView) rowView
                    .findViewById(R.id.icon);
            rowView.setTag(viewHolder);
        }
        // fill data
        ViewHolder holder = (ViewHolder) rowView.getTag();
        ParseUser u = users.get(position);
        String s = u.get("Name").toString();
        holder.text.setText(s);
        holder.image.setImageResource(R.drawable.yes);
        return rowView;
    }
}
