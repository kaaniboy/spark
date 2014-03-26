package com.spark;

import android.graphics.Bitmap;

public class FacebookPost {
	private String message;
	private String URL;
	private String imageURL;
	private String date;
	
	public FacebookPost(String mess, String address, String imageAddress, String d) {
		message = mess;
		URL = address;
		imageURL = imageAddress;
		date = d;
	}
	
	public boolean hasImage() {
		return imageURL != null;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String d) {
		date = d;
	}

	
}
