package com.spark.firestations;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.firestations.R;

public class FireStationDetails extends Activity {
	Station station;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fire_station_details);
		Intent intent = getIntent();
		station = (Station) intent.getParcelableExtra("station");
//		System.out.println("Displaying details for "+station);
//		System.out.println(station.latitude);
		LinearLayout linear = (LinearLayout) findViewById(R.id.detailsLinearLayout);
		TableLayout table = new TableLayout(this);
		FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
				
		        ViewGroup.LayoutParams.MATCH_PARENT,
		        300);//ViewGroup.LayoutParams.MATCH_PARENT);
		
		table.setLayoutParams(lp);
		table.setStretchAllColumns(true);
		linear.addView(table);
		TableLayout.LayoutParams rowLp = new TableLayout.LayoutParams(
		        ViewGroup.LayoutParams.MATCH_PARENT,
		        ViewGroup.LayoutParams.MATCH_PARENT,
		        1.0f);
		TableRow.LayoutParams cellLp = new TableRow.LayoutParams(
		        ViewGroup.LayoutParams.MATCH_PARENT,
		        ViewGroup.LayoutParams.MATCH_PARENT,
		        1.0f);
		TableRow row;
		TextView t;
		final float textSize = 18.0f;
		row = new TableRow(this);
		t = new TextView(this); t.setTextSize(textSize);
		t.setText("Fire Station:");
		row.addView(t, cellLp);
		t = new TextView(this); t.setTextSize(textSize);
		t.setText("#"+station.stationNumber);
		row.addView(t, cellLp);
		table.addView(row, rowLp);
		
		row = new TableRow(this);
		t = new TextView(this); t.setTextSize(textSize);
		t.setText("Address:");
		row.addView(t, cellLp);
		t = new TextView(this); t.setTextSize(textSize);
		t.setText(station.address);
		row.addView(t, cellLp);
		table.addView(row, rowLp);
		
		row = new TableRow(this);
		t = new TextView(this); t.setTextSize(textSize);
		t.setText("Zip Code:");
		row.addView(t, cellLp);
		t = new TextView(this); t.setTextSize(textSize);
		t.setText(station.zipcode);
		row.addView(t, cellLp);
		table.addView(row, rowLp);
		
		row = new TableRow(this);
		t = new TextView(this); t.setTextSize(textSize);
		t.setText("Phone Number:");
		row.addView(t, cellLp);
		t = new TextView(this); t.setTextSize(textSize);
		t.setText(station.phone);
		row.addView(t, cellLp);
		table.addView(row, rowLp);

		ImageView iv = new ImageView(this);
		int im = 0;
		switch (station.stationNumber){
		case 1: im=R.drawable.first; break;
		case 2: im=R.drawable.second; break;
		case 3: im=R.drawable.third; break;
		case 4: im=R.drawable.fourth; break;
		case 5: im=R.drawable.fifth; break;
		case 6: im=R.drawable.sixth; break;
		case 7: im=R.drawable.seventh; break;
		case 8: im=R.drawable.eighth; break;
		case 10: im=R.drawable.tenth; break;
		case 11: im=R.drawable.eleventh; break;
		}
		if (im != 0) iv.setBackgroundResource(im);
		linear.addView(iv, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f));
	}

}
