package com.example.lex.bx24list

import android.content.Context
import android.graphics.*
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import org.jetbrains.anko.dip
import org.jetbrains.anko.wrapContent

class MainView(context: Context) : View(context) {
    private var bitmap : Bitmap? = null
    private var title : String = "123"

    private val titlePaint = TextPaint().apply{
        textSize = dip(20).toFloat()
        color = Color.GRAY
        textAlign = Paint.Align.LEFT
    }
    private val bitmapPaint = Paint()

    override fun onDraw(canvas: Canvas?) {
        if (canvas == null) return
        val bitmapWidth = dpToPx(32, context)
        val textWidth = canvas.width - bitmapWidth - dpToPx(20,context)
        val textLayout = StaticLayout(title,titlePaint, textWidth, Layout.Alignment.ALIGN_NORMAL,1.0f,1.0f,false)

        canvas.drawColor(Color.LTGRAY)

        if (bitmap != null) {
            canvas.drawBitmap(bitmap,0f,0f,bitmapPaint)
        }
        val x = bitmapWidth.toFloat() + dpToPx(10,context)
        val y = (canvas.height-textLayout.height)/2f
        canvas.save()
        canvas.translate(x,y)
        textLayout.draw(canvas)
        canvas.restore()
    }

    fun bind(item: String) {
        title = item
        bitmap = null
        invalidate()
        val bitmapUrl = "https://"

        Glide.with(context)
                .asBitmap()
                .load(bitmapUrl)
                .listener(object : RequestListener<Bitmap> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Bitmap>?, isFirstResource: Boolean): Boolean {
                        return true
                    }
                    override fun onResourceReady(resource: Bitmap?, model: Any?, target: Target<Bitmap>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        if (resource == null) return false
                        bitmap = resource
                        invalidate()
                        return true
                    }
                })
                .submit(dpToPx(80,context),0)
        Log.v("text", bitmap.toString())
    }


}