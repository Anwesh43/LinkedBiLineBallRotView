package com.anwesh.uiprojects.bilineballrotview

/**
 * Created by anweshmishra on 03/08/19.
 */

import android.view.View
import android.view.MotionEvent
import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.Color

val nodes : Int = 5
val lines : Int = 2
val parts : Int = 2
val scGap : Float = 0.05f
val scDiv : Double = 0.51
val strokeFactor : Int = 90
val sizeFactor : Float = 90f
val foreColor : Int = Color.parseColor("#6A1B9A")
val strokeColor : Int = Color.parseColor("#BDBDBD")
val rFactor : Float = 2.9f

fun Int.inverse() : Float = 1f / this
fun Float.scaleFactor() : Float = Math.floor(this / scDiv).toFloat()
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.mirrorValue(a : Int, b : Int) : Float {
    val k : Float  = scaleFactor()
    return (1 - k) * a.inverse() + k * b.inverse()
}
fun Float.updateValue(dir : Float, a : Int, b : Int) : Float = mirrorValue(a, b) * dir * scGap

fun Canvas.drawBiLineBall(i : Int, sc : Float, size : Float, paint : Paint) {
    for (j in 0..(lines - 1)) {
        save()
        translate(0f, -size)
        rotate(45f * (1 - 2 * j) * sc.divideScale(j, lines))
        drawLine(0f, 0f, 0f, size, paint)
        drawCircle(0f, size, size / rFactor, paint)
        restore()
    }
}

fun Canvas.drawBiLineBallParts(sc1 : Float, sc2 : Float, size : Float, paint : Paint) {
    for (j in 0..(parts - 1)) {
        save()
        rotate(90f * (1f - 2 * j) * sc2.divideScale(j, parts))
        drawBiLineBall(j, sc1, size, paint)
        restore()
    }
}

fun Canvas.drawBLBRNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = h / (nodes + 1)
    val size : Float = gap / sizeFactor
    val sc1 : Float = scale.divideScale(0, 2)
    val sc2 : Float = scale.divideScale(1, 2)
    paint.color = foreColor
    paint.strokeCap = Paint.Cap.ROUND
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    save()
    translate(w / 2, gap * (i + 1))
    drawBiLineBallParts(sc1, sc2, size, paint)
    restore()
}

class BiLineBallRotView(ctx : Context) : View(ctx) {

    private val paint : Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas : Canvas) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }
}