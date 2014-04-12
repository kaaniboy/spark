package com.spark.firestations;

import android.os.Parcel;
import android.os.Parcelable;


public class Station implements Parcelable {
	String address, zipcode, opened, phone;
	double latitude, longitude;
	int stationNumber;

	public Station(int stationNumber, String address, String city, String zipcode,
				   double latitude, double longitude, String opened, String phone) {
		this.stationNumber = stationNumber;
		this.address = address;
		this.zipcode = zipcode;
		this.latitude = latitude;
		this.longitude = longitude;
		this.opened = opened;
		this.phone = phone;
	}

    /* everything below here is for implementing Parcelable */

    // 99.9% of the time you can just ignore this
    public int describeContents() {
        return 0;
    }

    // write your object's data to the passed-in Parcel
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(address);
        out.writeString(zipcode);
        out.writeString(opened);
        out.writeString(phone);
        out.writeDouble(latitude);
        out.writeDouble(longitude);
        out.writeInt(stationNumber);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Station> CREATOR = new Parcelable.Creator<Station>() {
        public Station createFromParcel(Parcel in) {
            return new Station(in);
        }

        public Station[] newArray(int size) {
            return new Station[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with its values
    private Station(Parcel in) {
    	address = in.readString();
    	zipcode = in.readString();
    	opened = in.readString();
    	phone = in.readString();
    	latitude = in.readDouble();
    	longitude = in.readDouble();
    	stationNumber = in.readInt();
    }

}
