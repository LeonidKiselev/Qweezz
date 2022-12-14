package com.example.qweezz

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class Solve : AppCompatActivity() {
    var id = ""
    var N = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.solve)
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
    }
    fun writeUserAnswer(id: String, N: String, answers: Set<String>) {
        val editor = this.getSharedPreferences("$id: $N", MODE_PRIVATE).edit()
        editor.putStringSet("user answer", answers)
        editor.apply()
    }
    fun writeResult(id: String, N: String) {
        val editor = this.getSharedPreferences("$id: $N", MODE_PRIVATE).edit()
        val usersAnswers = this.getSharedPreferences("$id: $N", MODE_PRIVATE).getStringSet("user answer", setOf())
        val correctAnswers = this.getSharedPreferences("$id: $N", MODE_PRIVATE).getStringSet("correct answers", setOf())
        if (usersAnswers == correctAnswers) {
            editor.putBoolean("result", true)
        } else {
            editor.putBoolean("result", false)
        }
        editor.apply()
    }
    fun onClickAnswer(view: View) {
        val itemsSet = this.getSharedPreferences("$id: $N", MODE_PRIVATE).getStringSet("answers", setOf())
        val itemsList = mutableListOf<String>()
        if (itemsSet != null) {
            for (item in itemsSet) {
                itemsList.add(item.substring(0, 1))
            }
        }
        val items = itemsList.joinToString("\n").split("\n").toTypedArray().sortedArray()
        val choice = this.getSharedPreferences("$id: $N", MODE_PRIVATE).getString("choice", "")
        val answers = mutableSetOf<String>()

        if (choice == "single choice") {
            val builder = AlertDialog.Builder(this)
            with(builder) {
                setTitle("choose ONE correct answer")
                setItems(items) { _, which ->
                    val answer = items[which]
                    answers.add(answer)
                    writeUserAnswer(id, N, answers)
                    writeResult(id, N)
                }
                show()
            }
        }

        if (choice == "multiple choice") {
            val choices = mutableSetOf<String>()
            val builder = AlertDialog.Builder(this)
            builder.setTitle("choose correct answers")
            builder.setMultiChoiceItems(items, null) { _, which, isSelected ->
                val item = items[which]
                if (isSelected) {
                    choices.add(item)
                } else if (choices.contains(item)) {
                    choices.remove(item)
                }
            }
            builder.setPositiveButton("ok") { _, _ ->
                for (item in choices) {
                    answers.add(item)
                }
                writeUserAnswer(id, N, answers)
                writeResult(id, N)
            }
            builder.show()
        }
    }
    fun onClickNext(view: View) {
        if (this.getSharedPreferences("$id: $N", MODE_PRIVATE).getStringSet("user answer", setOf()).isNullOrEmpty()) {
            Toast.makeText(this, "choose answer", Toast.LENGTH_SHORT).show()
            return
        }
        val n = (N.toInt() + 1).toString()
        if (this.getSharedPreferences("$id: $n", MODE_PRIVATE).getString("question", "").isNullOrEmpty()) {
            Toast.makeText(this, "all questions solved", Toast.LENGTH_SHORT).show()
            return
        }
        val intent = Intent(this, Solve::class.java)
        intent.putExtra("id", id)
        intent.putExtra("N", (N.toInt() + 1).toString())
        startActivity(intent)
    }
    fun onClickFinish(view: View) {
        if (this.getSharedPreferences("$id: $N", MODE_PRIVATE).getStringSet("user answer", setOf()).isNullOrEmpty()) {
            Toast.makeText(this, "choose answer", Toast.LENGTH_SHORT).show()
            return
        }
        val intent = Intent(this, Results::class.java)
        intent.putExtra("id", id)
        intent.putExtra("N", N)
        startActivity(intent)
    }
}