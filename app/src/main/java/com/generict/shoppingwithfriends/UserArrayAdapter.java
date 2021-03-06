package com.generict.shoppingwithfriends;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.List;

public class UserArrayAdapter extends ArrayAdapter<ParseUser> {
    @SuppressWarnings("WeakerAccess")
    public static final String TAG = "UserArrayAdapter";
    private final Activity context;
    private final List<ParseUser> users;


    /**
     * ViewHolder that gets fed into UserArrayAdapter
     */
    static class ViewHolder {
        public TextView text;
        public ImageView image;
    }


    /**
     * adapter taking in users in order to make the listView dynamic
     * @param context default
     * @param textViewResourceId default id
     * @param us list of users
     */
    @SuppressWarnings("SameParameterValue")
    public UserArrayAdapter(Activity context, int textViewResourceId, List<ParseUser> us) {
        super(context, textViewResourceId, us);
        this.context = context;
        this.users = us;
    }

    @SuppressLint("InflateParams")
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
        // fetches updates from cloud if needed
        try {
            u.fetchIfNeeded();
        } catch (ParseException e) {
            Log.e(TAG, "Error getting parse user: " + e.getMessage());
        }
        String s = u.get("Name").toString();
        holder.text.setText(s);
        holder.image.setImageResource(R.drawable.yes);
        return rowView;
    }
}
