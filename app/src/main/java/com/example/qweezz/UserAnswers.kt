package com.example.qweezz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class UserAnswers : AppCompatActivity() {
    var id = ""
    var N = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_answers)

        id = intent.getStringExtra("id").toString()
        N = intent.getStringExtra("N").toString()
        val question = this.getSharedPreferences("$id: $N", MODE_PRIVATE).getString("question", "")
        val editQuestion = findViewById<TextView>(R.id.editTextQuestion)
        editQuestion.text = question
        val answers = this.getSharedPreferences("$id: $N", MODE_PRIVATE).getStringSet("answers", setOf())
        if (answers != null) {
            for (answer in answers) {
                val n = answer.substring(0, 1)
                if (n == "1") {
                    val textAnswer1 = findViewById<TextView>(R.id.text1)
                    textAnswer1.text = n
                    val editAnswer1 = findViewById<TextView>(R.id.editText1)
                    editAnswer1.text = answer.substring(3, answer.length)
                }
                if (n == "2") {
                    val textAnswer2 = findViewById<TextView>(R.id.text2)
                    textAnswer2.text = n
                    val editAnswer2 = findViewById<TextView>(R.id.editText2)
                    editAnswer2.text = answer.substring(3, answer.length)
                }
                if (n == "3") {
                    val textAnswer3 = findViewById<TextView>(R.id.text3)
                    textAnswer3.text = n
                    val editAnswer3 = findViewById<TextView>(R.id.editText3)
                    editAnswer3.text = answer.substring(3, answer.length)
                }
                if (n == "4") {
                    val textAnswer4 = findViewById<TextView>(R.id.text4)
                    textAnswer4.text = n
                    val editAnswer4 = findViewById<TextView>(R.id.editText4)
                    editAnswer4.text = answer.substring(3, answer.length)
                }
                if (n == "5") {
                    val textAnswer5 = findViewById<TextView>(R.id.text5)
                    textAnswer5.text = n
                    val editAnswer5 = findViewById<TextView>(R.id.editText5)
                    editAnswer5.text = answer.substring(3, answer.length)
                }
                if (n == "6") {
                    val textAnswer6 = findViewById<TextView>(R.id.text6)
                    textAnswer6.text = n
                    val editAnswer6 = findViewById<TextView>(R.id.editText6)
                    editAnswer6.text = answer.substring(3, answer.length)
                }
            }
        }
        val correctAnswers = this.getSharedPreferences("$id: $N", MODE_PRIVATE).getStringSet("correct answers", setOf())?.sorted()
        val userAnswers = this.getSharedPreferences("$id: $N", MODE_PRIVATE).getStringSet("user answer", setOf())?.sorted()
        val correct = findViewById<TextView>(R.id.textCorrectAnswers)
        if (correctAnswers != null) {
            correct.text = correctAnswers.joinToString(" ")
        }
        val user = findViewById<TextView>(R.id.textUserAnswers)
        if (userAnswers != null) {
            user.text = userAnswers.joinToString(" ")
        }
    }
    fun onClickNext(view: View) {
        N = (N.toInt() + 1).toString()
        if (this.getSharedPreferences("$id: $N", MODE_PRIVATE).getString("question", "").isNullOrEmpty()) {
            Toast.makeText(this, "all answers viewed", Toast.LENGTH_SHORT).show()
            return
        }
        val intent = Intent(this, UserAnswers::class.java)
        intent.putExtra("id", id)
        intent.putExtra("N", N)
        startActivity(intent)
    }
    fun onClickFinish(view: View) {
        val intent = Intent(this, Start::class.java)
        startActivity(intent)
    }
}