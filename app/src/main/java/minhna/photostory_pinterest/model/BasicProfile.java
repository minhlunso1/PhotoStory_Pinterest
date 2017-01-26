package minhna.photostory_pinterest.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 26-Jan-17.
 */

public class BasicProfile implements Parcelable {

    private String url;
    //snake name for gson parsing
    private String first_name;
    private String last_name;
    private String id;

    protected BasicProfile(Parcel in) {
        url = in.readString();
        first_name = in.readString();
        last_name = in.readString();
        id = in.readString();
    }

    public static final Creator<BasicProfile> CREATOR = new Creator<BasicProfile>() {
        @Override
        public BasicProfile createFromParcel(Parcel in) {
            return new BasicProfile(in);
        }

        @Override
        public BasicProfile[] newArray(int size) {
            return new BasicProfile[size];
        }
    };

    public String getFullName() {
        return first_name+" "+last_name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(url);
        parcel.writeString(first_name);
        parcel.writeString(last_name);
        parcel.writeString(id);
    }
}
