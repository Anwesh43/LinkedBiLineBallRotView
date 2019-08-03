package com.anwesh.uiprojects.linkedbilineballrotview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anwesh.uiprojects.bilineballrotview.BiLineBallRotView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BiLineBallRotView.create(this)
    }
}
