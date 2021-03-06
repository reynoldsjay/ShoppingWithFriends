package com.generict.shoppingwithfriends;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseException;

import java.util.List;

/**
 * Adapts Item objects for list view
 */
public class ItemArrayAdapter extends ArrayAdapter<Item> {
    private static final String TAG = "ItemArrayAdapter";
    private final Activity context;
    private final List<Item> items;

    /**
     * Views for text and image
     */
    static class ViewHolder {
        public TextView text;
        public ImageView image;
    }

    /**
     * Array adaptor for holding items
     * @param context activity, calls post sales report activity
     * @param textViewResourceId links to global var
     * @param us list of items
     */
    @SuppressWarnings("SameParameterValue")
    public ItemArrayAdapter(Activity context, int textViewResourceId, List<Item> us) {
        //noinspection RedundantCast
        super((Context) context, textViewResourceId, us);
        this.context = context;
        this.items = us;
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
        Item u = items.get(position);
        // fetches data from cloud if needed
        try {
            u.fetchIfNeeded();
            String s = u.getName();
            holder.text.setText(s);
            holder.image.setImageResource(R.drawable.yes);
        } catch (ParseException e) {
            Log.e(TAG, "Exception in get item query: " + e);
        }
        return rowView;
    }
}
