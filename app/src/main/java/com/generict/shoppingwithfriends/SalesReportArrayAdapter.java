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

import java.util.List;

/**
 * Adapts Sales Report objects for list view
 */
class SalesReportArrayAdapter extends ArrayAdapter<SalesReport> {
    private static final String TAG = "SalesReportArrayAdapter";
    private final Activity context;
    private final List<SalesReport> items;

    /**
     * for visualization
     */
    static class ViewHolder {
        public TextView text;
        public ImageView image;
    }

    /**
     * Adaptor for holding sales report
     * @param context of activity class
     * @param textViewResourceId default id
     * @param us list of sales reports
     */
    @SuppressWarnings("SameParameterValue")
    public SalesReportArrayAdapter(Activity context, int textViewResourceId, List<SalesReport> us) {
        super(context, textViewResourceId, us);
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
        SalesReport u = items.get(position);
        // fetches data from cloud if needed
        try {
            u.fetchIfNeeded();
        } catch (ParseException e) {
            Log.e(TAG, "Error getting sales report : " + e.getMessage());
        }
        String s = u.getName();
        holder.text.setText(s);
        holder.image.setImageResource(R.drawable.yes);
        return rowView;
    }
}
