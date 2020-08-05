package com.lulu.parcelsavetolocal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ContinueSaver.FILE_PATH = filesDir.absoluteFile.absolutePath + "continueReadMark.obj"
        val testParcel = TestParcel("真是 TMD 牛逼", "234", 10)

        writeParcel.setOnClickListener {
            ContinueSaver.saveContinueMarkToLocal(testParcel)
            Toast.makeText(this, "写入完成", Toast.LENGTH_SHORT).show()
        }
        readParcel.setOnClickListener {
            val obj = ContinueSaver.readContinueMarkFromLocal()
            Toast.makeText(this, "title: ${obj?.title} bid: ${obj?.bid} cid: ${obj?.cid} ", Toast.LENGTH_SHORT).show()
        }
        testBtn.setOnClickListener {
            val bytes = ParcelUtils.marshall(testParcel)
            val parcel = ParcelUtils.unmarshall(bytes)
            val obj = TestParcel.CREATOR.createFromParcel(parcel)
            Toast.makeText(this, "title: ${obj?.title} bid: ${obj?.bid} cid: ${obj?.cid} ", Toast.LENGTH_SHORT).show()
        }
    }
}