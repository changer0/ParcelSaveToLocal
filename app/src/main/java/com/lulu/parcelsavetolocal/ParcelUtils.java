package com.lulu.parcelsavetolocal;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * @author zhanglulu on 2020/8/5.
 * for
 */
class ParcelUtils {

    public static byte[] marshall(Parcelable parceable) {
        Parcel parcel = Parcel.obtain();
        parcel.setDataPosition(0);
        parceable.writeToParcel(parcel, 0);
        byte[] bytes = parcel.marshall();

        Log.d("ParcelableTest", "bytes = " + String.valueOf(bytes) + "parcel" + parcel.toString());
        parcel.recycle();
        return bytes;
    }

    public static Parcel unmarshall(byte[] bytes) {
        Parcel parcel = Parcel.obtain();
        parcel.unmarshall(bytes, 0, bytes.length);
        parcel.setDataPosition(0);
        return parcel;
    }
}
