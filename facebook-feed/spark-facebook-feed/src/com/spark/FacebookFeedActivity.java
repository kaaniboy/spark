package com.spark;

import java.util.ArrayList;

import com.spark.R;
import com.spark.tasks.RetrievePostsTask;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

public class FacebookFeedActivity extends ActionBarActivity implements
		AsyncTaskListener<ArrayList<FacebookPost>> {

	FacebookFeedAdapter adapter;
	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_facebook_feed);
		listView = (ListView) findViewById(R.id.list_view);

		new RetrievePostsTask(this).execute(0);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.facebook_feed, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return true;
	}

	@Override
	public void onTaskComplete(ArrayList<FacebookPost> data) {
		for (FacebookPost p : data) {
			if (p.hasImage()) {
				System.out.println(p.getImageURL());
			}
		}

		adapter = new FacebookFeedAdapter(this, data);
		listView.setAdapter(adapter);

	}

}
