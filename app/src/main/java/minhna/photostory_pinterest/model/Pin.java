package minhna.photostory_pinterest.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.pinterest.android.pdk.PDKPin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 25-Jan-17.
 */

public class Pin implements Parcelable {

    public String note;
    public String imageUrl;
    public int commentCount;
    public boolean isLove;

    public Pin(String note, String imageUrl, int commentCount) {
        this.note = note;
        this.imageUrl = imageUrl;
        this.commentCount = commentCount;
        this.isLove = false;
    }

    protected Pin(Parcel in) {
        note = in.readString();
        imageUrl = in.readString();
        commentCount = in.readInt();
        isLove = in.readByte() != 0;
    }

    public static final Creator<Pin> CREATOR = new Creator<Pin>() {
        @Override
        public Pin createFromParcel(Parcel in) {
            return new Pin(in);
        }

        @Override
        public Pin[] newArray(int size) {
            return new Pin[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(note);
        parcel.writeString(imageUrl);
        parcel.writeInt(commentCount);
        parcel.writeByte((byte) (isLove ? 1 : 0));
    }

    @Nullable
    public static List<Pin> castToMyList(List<PDKPin> pdkPins) {
        List<Pin> pins = new ArrayList<>();
        for (PDKPin tmp: pdkPins) {
            String note = tmp.getNote()!=null ? tmp.getNote():null;
            String imageUrl = tmp.getImageUrl()!=null ? tmp.getImageUrl():null;
            Integer commentCount = tmp.getCommentCount()!=null ? tmp.getCommentCount():0;
            Pin pin = new Pin(note, imageUrl, commentCount);
            pins.add(pin);
        }
        return pins.size() > 0 ? pins:null;
    }
}
