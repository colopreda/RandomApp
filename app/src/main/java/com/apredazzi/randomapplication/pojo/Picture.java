package com.apredazzi.randomapplication.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class Picture implements Parcelable {
    private String thumbnail;
    private String large;

    public Picture(final String thumbnail, final String large) {
        this.thumbnail = thumbnail;
        this.large = large;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getLarge() {
        return large;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.thumbnail);
        dest.writeString(this.large);
    }

    protected Picture(Parcel in) {
        this.thumbnail = in.readString();
        this.large = in.readString();
    }

    public static final Parcelable.Creator<Picture> CREATOR = new Parcelable.Creator<Picture>() {
        @Override
        public Picture createFromParcel(Parcel source) {
            return new Picture(source);
        }

        @Override
        public Picture[] newArray(int size) {
            return new Picture[size];
        }
    };
}
