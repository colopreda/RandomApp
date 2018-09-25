package com.apredazzi.randomapplication.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class Users implements Parcelable {
    private String email;
    private Name name;
    private Login login;
    private Picture picture;

    public Users(final String email, final Name name, final Login login, final Picture picture) {
        this.email = email;
        this.name = name;
        this.login = login;
        this.picture = picture;
    }

    public String getEmail() {
        return email;
    }

    public Name getName() {
        return name;
    }

    public Login getLogin() {
        return login;
    }

    public Picture getPicture() {
        return picture;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.email);
        dest.writeParcelable(this.name, flags);
        dest.writeParcelable(this.login, flags);
        dest.writeParcelable(this.picture, flags);
    }

    protected Users(Parcel in) {
        this.email = in.readString();
        this.name = in.readParcelable(Name.class.getClassLoader());
        this.login = in.readParcelable(Login.class.getClassLoader());
        this.picture = in.readParcelable(Picture.class.getClassLoader());
    }

    public static final Parcelable.Creator<Users> CREATOR = new Parcelable.Creator<Users>() {
        @Override
        public Users createFromParcel(Parcel source) {
            return new Users(source);
        }

        @Override
        public Users[] newArray(int size) {
            return new Users[size];
        }
    };
}
