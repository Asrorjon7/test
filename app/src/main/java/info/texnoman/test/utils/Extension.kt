package info.texnoman.test.utils

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.widget.ImageView
import android.widget.SeekBar
import info.texnoman.test.R
import net.glxn.qrgen.android.QRCode

fun  ImageView.setBackground(){
    this.setImageResource(R.color.green)
}
fun Context.getPositionData(position:Int):Bitmap{
    var listQr=this.resources.getStringArray(R.array.algorithm)
    return  QRCode.from(listQr[position]).bitmap()
}


