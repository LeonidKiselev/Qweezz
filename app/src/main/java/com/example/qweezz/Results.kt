package com.example.qweezz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class Results : AppCompatActivity() {
    var id = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.results)

        id = intent.getStringExtra("id").toString()
        val n = intent.getStringExtra("N").toString().toInt()
        var userPoints = 0
        for (i in 1..n) {
            val N = i.toString()
            val result = this.getSharedPreferences("$id: $N", MODE_PRIVATE).getBoolean("result", false)
            if (result) {
                userPoints += 1
            }
        }

        val textAllPoints = findViewById<TextView>(R.id.textAllPoints)
        textAllPoints.text = n.toString()
        val textUserPoints = findViewById<TextView>(R.id.textUserPoints)
        textUserPoints.text = userPoints.toString()
    }
    fun onClickBack(view: View) {
        val intent = Intent(this, Start::class.java)
        startActivity(intent)
    }
    fun onClickView(view: View) {
        val intent = Intent(this, UserAnswers::class.java)
        intent.putExtra("id", id)
        intent.putExtra("N", "1")
        startActivity(intent)
    }
}