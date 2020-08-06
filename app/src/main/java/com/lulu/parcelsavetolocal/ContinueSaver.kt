package com.lulu.parcelsavetolocal

import android.os.Parcel
import android.text.TextUtils
import java.io.*

/**
 * @author zhanglulu on 2020/8/5.
 * for
 */
object ContinueSaver {
    public var FILE_PATH = ""
    @JvmStatic
    public fun saveContinueMarkToLocal(mark: TestParcel?) {
        if (mark == null) {
            return
        }

        var fos : FileOutputStream? = null
        var bos : BufferedOutputStream? = null
        var oos : ObjectOutputStream? = null
        try {
            val file = File(FILE_PATH)
            if (file.exists()) {
                file.delete()
            }
            fos = FileOutputStream(file)
            bos = BufferedOutputStream(fos)
            oos = ObjectOutputStream(bos)
            oos.write(ParcelUtils.marshall(mark))
            oos.flush()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                oos?.close()
                bos?.close()
                fos?.close()
            } catch (e: Exception) {}
        }
    }

    @JvmStatic
    public fun readContinueMarkFromLocal(): TestParcel? {
        var mark: TestParcel? = null
        var fis : FileInputStream? = null
        var bis : BufferedInputStream? = null
        var ois : ObjectInputStream? = null
        try {
            fis = FileInputStream(FILE_PATH)
            bis = BufferedInputStream(fis)
            ois = ObjectInputStream(bis)
            val bytes = ois.readBytes()
            val parcel = ParcelUtils.unmarshall(bytes)
            mark = TestParcel.CREATOR.createFromParcel(parcel)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                ois?.close()
                bis?.close()
                fis?.close()
            } catch (e: Exception) {}
        }

        if (mark == null || TextUtils.isEmpty(mark.bid)) {
            return null
        }

        return mark
    }

}