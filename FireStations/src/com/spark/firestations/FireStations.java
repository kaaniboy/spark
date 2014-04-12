package com.spark.firestations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.firestations.R;

public class FireStations extends Activity {
	ListView listView;
	Map<String, Station> stationData;
	double userLatitude, userLongitude;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fire_stations);
        listView = (ListView) findViewById(R.id.listView1);
        double[] gps = getGPS();
        System.out.printf("(%f, %f)\n", gps[0], gps[1]);
        userLatitude = gps[0];
        userLongitude = gps[1];
        stationData = new HashMap<String, Station>();
        stationData.put("Fire Station #1", new Station(1, "2730 E. Williams Field Rd.", "Gilbert", "85295", 33.30737, -111.731372, "December 2006", "(480) 503-6300"));
        stationData.put("Fire Station #2", new Station(2, "2855 E. Guadalupe Rd.", "Gilbert", "85234", 33.364406, -111.727717, "1991 / April 2006", "(480) 503-6300"));
        stationData.put("Fire Station #3", new Station(3, "1011 E. Guadalupe Rd.", "Gilbert", "85234", 33.3642, -111.769232, "March 1998 / June 2008", "(480) 503-6300"));
        stationData.put("Fire Station #4", new Station(4, "909 E. Ray Rd.", "Gilbert", "85296", 33.320683, -111.770847, "October 1999", "(480) 503-6300"));
        stationData.put("Fire Station #5", new Station(5, "3630 E. Germann Rd.", "Gilbert", "85297", 33.2782, -111.709574, "March 2005", "(480) 503-6300"));
        stationData.put("Fire Station #6", new Station(6, "3595 E. Warner Rd.", "Gilbert", "85296", 33.335246, -111.712097, "February 2005", "(480) 503-6300"));
        stationData.put("Fire Station #7", new Station(7, "215 N. Cooper Rd.", "Gilbert", "85233", 33.354195, -111.806271, "February 1994", "(480) 503-6300"));
        stationData.put("Fire Station #8", new Station(8, "1095 E. Germann Rd.", "Gilbert", "85297", 33.277202, -111.764758, "June 2006", "(480) 503-6300"));
        stationData.put("Fire Station #10", new Station(10, "1330 W. Guadalupe Rd.", "Gilbert", "85233", 33.364706, -111.818596, "July 2012", "(480) 503-6300"));
        stationData.put("Fire Station #11", new Station(11, "2860 E. Riggs Rd.", "Gilbert", "85298", 33.219769, -111.727636, "November 2003", "(480) 503-6300"));
        
        List<String> values = new ArrayList<String>(stationData.keySet());
        
//        System.out.println("Sorting");
//        System.out.println(values.toString());
        Collections.sort(values, new StationComparator(userLatitude, userLongitude));
//        System.out.println(values.toString());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
          android.R.layout.simple_list_item_1, android.R.id.text1, values);
        
        listView.setAdapter(adapter); 
        listView.setOnItemClickListener(new OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            	  int itemPosition = position;
            	  String name = (String)listView.getItemAtPosition(position);
            	  System.out.println(name);
            	  Intent details = new Intent(FireStations.this, FireStationDetails.class);
            	  Station s = stationData.get(name);
            	  System.out.println("In here with "+s.address);
            	  details.putExtra("station", s);
            	  FireStations.this.startActivity(details);
               
              }
         });
	}
	private double[] getGPS() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);  
        List<String> providers = lm.getProviders(true);
        Location l = null;
        
        for (int i=providers.size()-1; i>=0; i--) {
                l = lm.getLastKnownLocation(providers.get(i));
                if (l != null){
//                	System.out.println("Provider: "+providers.get(i));
                	break;
                }
        }
        
        double[] gps = new double[2];
        if (l != null) {
                gps[0] = l.getLatitude();
                gps[1] = l.getLongitude();
        }
        return gps;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_fire_stations, menu);
		return true;
	}
	
	class StationComparator implements Comparator<String>{
		double latitude, longitude;
		public StationComparator(double latitude, double longitude){
			this.latitude = latitude;
			this.longitude = longitude;
		}
		public int compare(String string1, String string2){
			/*
			 * Compares based on Euclidean distance from the user
			 */
			Station s1 = stationData.get(string1);
			Station s2 = stationData.get(string2);
			double dist = (s1.latitude - latitude)*(s1.latitude - latitude) + 
				          (s1.longitude - longitude)*(s1.longitude - longitude) -
				          (s2.latitude - latitude)*(s2.latitude - latitude) - 
				          (s2.longitude - longitude)*(s2.longitude - longitude);
			if (dist < 0){
				return -1;
			}else{
				if (dist == 0) return 0;
				else return 1;
			}
		}
	}

}
