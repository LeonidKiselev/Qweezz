package com.example.qweezz

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class Create : AppCompatActivity() {
    var id = ""
    var N = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create)
        id = intent.getStringExtra("id").toString()
        N = intent.getStringExtra("N").toString()
        val name = this.getSharedPreferences("qweezz ids", MODE_PRIVATE).getString(id, "").toString()
        writeName(id, N, name)
    }
    fun onClickSet(view: View) {
        val question = findViewById<EditText>(R.id.editTextQuestion).text.toString()
        writeQuestion(id, N, question)
        val answers = mutableSetOf<String>()

        val answer1 = findViewById<EditText>(R.id.editText1).text.toString()
        if (answer1.isNotEmpty() and answer1.isNotBlank()) {
            answers.add("1: $answer1")
        }

        val answer2 = findViewById<EditText>(R.id.editText2).text.toString()
        if (answer2.isNotEmpty() and answer2.isNotBlank()) {
            answers.add("2: $answer2")
        }

        val answer3 = findViewById<EditText>(R.id.editText3).text.toString()
        if (answer3.isNotEmpty() and answer3.isNotBlank()) {
            answers.add("3: $answer3")
        }

        val answer4 = findViewById<EditText>(R.id.editText4).text.toString()
        if (answer4.isNotEmpty() and answer4.isNotBlank()) {
            answers.add("4: $answer4")
        }

        val answer5 = findViewById<EditText>(R.id.editText5).text.toString()
        if (answer5.isNotEmpty() and answer5.isNotBlank()) {
            answers.add("5: $answer5")
        }

        val answer6 = findViewById<EditText>(R.id.editText6).text.toString()
        if (answer6.isNotEmpty() and answer6.isNotBlank()) {
            answers.add("6: $answer6")
        }

        writeAnswers(id, N, answers)
    }
    fun writeName(id: String, N: String, name: String) {
        val editor = this.getSharedPreferences("$id: $N", MODE_PRIVATE).edit()
        editor.putString("name", name)
        editor.apply()
    }
    fun writeQuestion(id: String, N: String, question: String) {
        val editor = this.getSharedPreferences("$id: $N", MODE_PRIVATE).edit()
        editor.putString("question", question)
        editor.apply()
    }
    fun writeAnswers(id: String, N: String, answers: Set<String>) {
        val editor = this.getSharedPreferences("$id: $N", MODE_PRIVATE).edit()
        editor.putStringSet("answers", answers)
        editor.apply()
    }
    fun writeChoice(id: String, N: String, choice: String) {
        val editor = this.getSharedPreferences("$id: $N", MODE_PRIVATE).edit()
        editor.putString("choice", choice)
        editor.apply()
    }
    fun writeCorrectAnswers(id: String, N: String, answers: Set<String>) {
        val editor = this.getSharedPreferences("$id: $N", MODE_PRIVATE).edit()
        editor.putStringSet("correct answers", answers)
        editor.apply()
    }
    fun onClickChoice(view: View) {
        if (this.getSharedPreferences("$id: $N", MODE_PRIVATE).getStringSet("answers", setOf()).isNullOrEmpty()) {
            Toast.makeText(this, "set question and answers", Toast.LENGTH_SHORT).show()
            return
        }
        val items = arrayOf("single choice", "multiple choice")
        val builder = AlertDialog.Builder(this)
        with(builder) {
            setTitle("choose choice option")
            setItems(items) { _, which ->
                val choice = items[which]
                writeChoice(id, N, choice)
            }
            show()
        }
    }
    fun onClickAnswers(view: View) {
        val choice = this.getSharedPreferences("$id: $N", MODE_PRIVATE).getString("choice", "")
        if (choice == "") {
            Toast.makeText(this, "set choice single or multiple", Toast.LENGTH_SHORT).show()
            return
        }
        val itemsSet = this.getSharedPreferences("$id: $N", MODE_PRIVATE).getStringSet("answers", setOf())
        val itemsList = mutableListOf<String>()
        if (itemsSet != null) {
            for (item in itemsSet) {
                itemsList.add(item.substring(0, 1))
            }
        }
        val items = itemsList.joinToString("\n").split("\n").toTypedArray().sortedArray()

        if (choice == "single choice") {
            val builder = AlertDialog.Builder(this)
            with(builder) {
                setTitle("choose ONE correct answer")
                setItems(items) { _, which ->
                    val answer = items[which]
                    val answers = setOf(answer)
                    writeCorrectAnswers(id, N, answers)
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
            val answers = mutableSetOf<String>()
            builder.setPositiveButton("ok") { _, _ ->
                for (item in choices) {
                    answers.add(item)
                }
                writeCorrectAnswers(id, N, answers)
            }
            builder.show()
        }
    }
    fun onClickNext(view: View) {
        if (this.getSharedPreferences("$id: $N", MODE_PRIVATE).getStringSet("correct answers", setOf()).isNullOrEmpty()) {
            Toast.makeText(this, "set correct answers", Toast.LENGTH_SHORT).show()
            return
        }
        val intent = Intent(this, Create::class.java)
        intent.putExtra("id", id)
        intent.putExtra("N", (N.toInt() + 1).toString())
        startActivity(intent)
    }
    fun onClickFinish(view: View) {
        if (this.getSharedPreferences("$id: $N", MODE_PRIVATE).getStringSet("correct answers", setOf()).isNullOrEmpty()) {
            Toast.makeText(this, "set correct answers", Toast.LENGTH_SHORT).show()
            return
        }
        val intent = Intent(this, Start::class.java)
        startActivity(intent)
    }
}