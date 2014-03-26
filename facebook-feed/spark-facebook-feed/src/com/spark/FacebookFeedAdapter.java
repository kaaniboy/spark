package com.spark;

import java.util.ArrayList;

import com.spark.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class FacebookFeedAdapter extends ArrayAdapter<FacebookPost>{
	 private final Context context;
	 private final ArrayList<FacebookPost> values;
	 
	public FacebookFeedAdapter(Context c, ArrayList<FacebookPost> v) {
		super(c, R.layout.facebook_list_item, v);
		context = c;
		values = v;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		    View rowView = inflater.inflate(R.layout.facebook_list_item, parent, false);
		    TextView messageView = (TextView) rowView.findViewById(R.id.message);
		    messageView.setText(values.get(position).getMessage());
		    
		    TextView dateView = (TextView) rowView.findViewById(R.id.date);
		    dateView.setText(values.get(position).getDate());
		    return rowView;
	}

}
