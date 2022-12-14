package com.example.qweezz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Start : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start)
    }
    fun onClickCreate(view: View) {
        val intent = Intent(this, QweezzName::class.java)
        startActivity(intent)
    }
    fun onClickSolve(view: View) {
        val intent = Intent(this, QweezzId::class.java)
        startActivity(intent)
    }
}