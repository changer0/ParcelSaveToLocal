package com.lulu.parcelsavetolocal;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author zhanglulu on 2020/8/5.
 * for
 */
public class TestParcel implements Parcelable {
    public String title;
    public String bid;
    public int cid;

    public TestParcel(String title, String bid, int cid) {
        this.title = title;
        this.bid = bid;
        this.cid = cid;
    }

    protected TestParcel(Parcel in) {
        title = in.readString();
        bid = in.readString();
        cid = in.readInt();
    }

    public static final Creator<TestParcel> CREATOR = new Creator<TestParcel>() {
        @Override
        public TestParcel createFromParcel(Parcel in) {
            return new TestParcel(in);
        }

        @Override
        public TestParcel[] newArray(int size) {
            return new TestParcel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(bid);
        dest.writeInt(cid);
    }
}
