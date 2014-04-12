package com.spark.firestations;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.FrameLayout;
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
		System.out.println("Displaying details for "+station);
		System.out.println(station.latitude);
		
		TableLayout table = new TableLayout(this);
		// Java. You succeed!
		FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
		        ViewGroup.LayoutParams.FILL_PARENT,
		        ViewGroup.LayoutParams.FILL_PARENT);
		table.setLayoutParams(lp);
		table.setStretchAllColumns(true);

		TableLayout.LayoutParams rowLp = new TableLayout.LayoutParams(
		        ViewGroup.LayoutParams.FILL_PARENT,
		        ViewGroup.LayoutParams.FILL_PARENT,
		        1.0f);
		TableRow.LayoutParams cellLp = new TableRow.LayoutParams(
		        ViewGroup.LayoutParams.FILL_PARENT,
		        ViewGroup.LayoutParams.FILL_PARENT,
		        1.0f);
		TableRow row;
		TextView t;
		final float textSize = 20.0f;
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

		
		/*		for (int r = 0; r < 8; ++r)
		{
		    TableRow row = new TableRow(this);
		    for (int c = 0; c < 2; ++c)
		    {
		        TextView t = new TextView(this);
		        t.setTextSize(textSize);
		        t.setText("Hello");
		        row.addView(t, cellLp);
		    }
		    table.addView(row, rowLp);
		}*/
		setContentView(table);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_fire_station_details, menu);
		return true;
	}

}
