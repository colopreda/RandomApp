package com.apredazzi.randomapplication.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class Login implements Parcelable {
    private String username;

    public Login(final String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
    }

    protected Login(Parcel in) {
        this.username = in.readString();
    }

    public static final Parcelable.Creator<Login> CREATOR = new Parcelable.Creator<Login>() {
        @Override
        public Login createFromParcel(Parcel source) {
            return new Login(source);
        }

        @Override
        public Login[] newArray(int size) {
            return new Login[size];
        }
    };
}
