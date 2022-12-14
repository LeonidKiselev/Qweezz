package com.example.qweezz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class QweezzName : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qweezz_name)
    }
    fun writeQweezz(name: String, id: String) {
        val editor = this.getSharedPreferences("qweezz ids", MODE_PRIVATE).edit()
        editor.putString(id, name)
        editor.apply()
    }
    fun onClickCreate(view: View) {
        val name = findViewById<EditText>(R.id.editTextNew).text.toString()
        val id = findViewById<EditText>(R.id.editTextId).text.toString()
        if (this.getSharedPreferences("qweezz ids", MODE_PRIVATE).contains(id)) {
            Toast.makeText(this, "this id exists", Toast.LENGTH_SHORT).show()
        } else if (id.isEmpty() or id.isBlank()) {
            Toast.makeText(this, "id can not be empty", Toast.LENGTH_SHORT).show()
        } else {
            writeQweezz(name, id)
            val intent = Intent(this, Create::class.java)
            intent.putExtra("id", id)
            intent.putExtra("N", "1")
            startActivity(intent)
        }
    }
}