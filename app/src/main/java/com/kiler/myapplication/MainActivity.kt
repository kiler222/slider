package com.kiler.myapplication

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MotionEventCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val DEBUG_TAG = "PJ "

    var bottom = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vto = viewBlock.viewTreeObserver

        vto.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout(){
                var rectf = Rect()
                viewBlock.getGlobalVisibleRect(rectf)
                bottom = rectf.bottom
                viewBlock.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })




        viewBlock.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent): Boolean {

                if (event.action == MotionEvent.ACTION_MOVE) {

                    val currentY = event.rawY.toInt()

                    val diff = bottom - currentY

                    Log.e(DEBUG_TAG, "bottom = $bottom, current = ${currentY}, new h = $diff")

                    if (diff > 0) {
                        viewBlock.layoutParams = viewBlock.layoutParams.apply {
                            height = diff
                        }
                    }

                }

                if (event.action == MotionEvent.ACTION_DOWN) {
                    val currentY = event.rawY.toInt()

                    val diff = bottom - currentY
                    viewBlock.layoutParams = viewBlock.layoutParams.apply {
                        height = diff
                    }


                    return true
                }

                return true
            }
        })


    }




}