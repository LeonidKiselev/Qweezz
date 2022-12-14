package com.example.qweezz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class QweezzId : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qweezz_id)
    }
    fun onClickSolve(view: View) {
        val id = findViewById<EditText>(R.id.editTextId).text.toString()
        if (!this.getSharedPreferences("qweezz ids", MODE_PRIVATE).contains(id)) {
            Toast.makeText(this, "id does not exist", Toast.LENGTH_SHORT).show()
        } else {
            val intent = Intent(this, Solve::class.java)
            intent.putExtra("id", id)
            intent.putExtra("N", "1")
            startActivity(intent)
        }
    }
}