package com.example.myapplication

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View.MeasureSpec
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


var bmap: Bitmap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.RGB_565)
var lastX: Float = 0.toFloat();
var lastY: Float = 0.toFloat();

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val color_green: Button = findViewById(R.id.but_green)
        color_green.setOnClickListener {
            val toast = Toast.makeText(this, "POOP!", Toast.LENGTH_SHORT)
            toast.show()
        }
        val bton: ImageView = findViewById(R.id.imageView)
        //bmap = (bton.getDrawable() as BitmapDrawable).bitmap
    }
    override fun onTouchEvent(e: MotionEvent): Boolean {
        val bton: ImageView = findViewById(R.id.imageView)
        val paint: Paint = Paint()
        paint.setColor(Color.GREEN) // установим зеленый цвет
        paint.setStyle(Paint.Style.FILL)
        paint.strokeWidth = 10.toFloat()
        if (e.action == 0) {
            lastX = e.x - bton.x
            lastY = e.y - bton.y - bton.height / 4
        }
        val cvas: Canvas = Canvas(bmap)
        cvas.drawLine(lastX, lastY,e.x - bton.x, e.y - bton.y - bton.height / 4, paint)
        lastX = e.x - bton.x
        lastY = e.y - bton.y - bton.height / 4
        //cvas.drawCircle(e.x - bton.x, e.y - bton.y - bton.height / 2, 10.toFloat(), paint)
        bton.setImageBitmap(bmap)
        return true
    }
}