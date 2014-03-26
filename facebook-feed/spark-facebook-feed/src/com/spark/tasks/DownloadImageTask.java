package com.spark.tasks;

import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;



public class  DownloadImageTask extends AsyncTask<Void, Void, Bitmap> {
 ImageView imageView;
 String url;

 public DownloadImageTask(ImageView bmImage, String u) {
     this.imageView = bmImage;
     this.url = u;
 }

 protected Bitmap doInBackground(Void... params) {
     Bitmap icon = null;
     try {
         InputStream in = new java.net.URL(url).openStream();
         icon = BitmapFactory.decodeStream(in);
     } catch (Exception e) {
    	 System.out.println(e.getMessage());
         e.printStackTrace();
     }
     return icon;
 }

 protected void onPostExecute(Bitmap result) {
     imageView.setImageBitmap(result);
     System.out.println("Loaded: " + url);
 }

}