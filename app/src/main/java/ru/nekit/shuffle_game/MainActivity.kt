package ru.nekit.shuffle_game

import android.os.Bundle
import android.text.Editable
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    private var currentQuestionNumber = 1
    private var inputWord: String = ""
    private var questions = listOf(
        "What are the two official languages for Android development? \n\n A) Kotlin and Java \n\n B) Java and Python \n\n C) Kotlin and Python",
        "How do you define a function in Kotlin? \n\n A) void \n\n B) var \n\n C) function",
        "What is a variable used for? \n\n A) To contain data \n\n B) To insert a random value \n\n C) Don't know",
        "What does SDK stand for in Android SDK? \n\n A) Software Development Kit \n\n B) Software Development Kotlin \n\n C) Something Don't Know"
    )

    private val words_dict = mutableMapOf(
        1 to "заяц",
        2 to "волк",
        3 to "вампир",
        4 to "метеор",
        5 to "катафалка",
        6 to "мизинец",
    )


    private var rightAnswers = listOf(1, 2, 1, 1)

    fun showKeyboard(activity: FragmentActivity) {
        val inputMethodManager =
            activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInputFromWindow(
            activity.currentFocus!!.windowToken,
            InputMethodManager.SHOW_FORCED,
            0
        )
    }

    fun shuffleLettersInWord(normalWord: String?): String {
        var shuffled: String
        val toCharArray = normalWord?.toCharArray()?.toList()
        return toCharArray?.shuffled().toString()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val myDialogFragment = MyDialogFragment()
        val supportFragmentManager = supportFragmentManager

        val transaction = supportFragmentManager.beginTransaction()
        myDialogFragment.show(
            transaction, "Правила игры!\n" +
                    "2ая строчка\n" +
                    "3я длинннннннннннннннннннннннаааая"
        )

//        val id = item.getItemId()
//        if (id == R.id.rulesButton) {
////            Toast.makeText(applicationContext, "Правила читаем!", Toast.LENGTH_SHORT).show()
//            val dialog = Dialog(applicationContext)
//            val ruleTextView = TextView(applicationContext)
//            ruleTextView.setText("Правила игры!\n2ая строчка\n3я длинннннннннннннннннннннннаааая")
//            ruleTextView.setTextSize(14f)
//            dialog.setContentView(ruleTextView)
//            dialog.show()
//        }
        return super.onOptionsItemSelected(item)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val questionTextView = findViewById<TextView>(R.id.questionTextView)
//        val bButton = findViewById<Button>(R.id.bButton)
        val okButton = findViewById<Button>(R.id.okButton)
        val tmpInputWordEditText = findViewById<EditText>(R.id.inputWordEditText)
        tmpInputWordEditText.requestFocus()

//        showKeyboard(sa)
//        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
//        val imm: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.toggleSoftInputFr(InputMethodManager.SHOW_FORCED, 0)
//        imm.showSoftInput(tmpInputWordEditText, 1)

//        tmpInputWordEditText.isEnabled = true
//        getSystemService(Context.INPUT_METHOD_SERVICE)

//        private fun View.showKeyboard() {
//            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
//        }

        questionTextView.setText(shuffleLettersInWord(words_dict.get(currentQuestionNumber)))

        tmpInputWordEditText.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                //Perform Code
                showToast(null, tmpInputWordEditText.text)
            }
            true
        }

        okButton.setOnClickListener {
            showToast(null, tmpInputWordEditText.text)
        }

//        bButton.setOnClickListener {
//            showQuestion();
//        }


    }


    private fun showQuestion() {
        findViewById<TextView>(R.id.questionTextView).setText(words_dict.get(currentQuestionNumber))
    }

    private fun showToast(answer: Int?, inputWord: Editable) {
        if (inputWord.toString().lowercase(Locale.ROOT) == words_dict.get(currentQuestionNumber)) {
            Toast.makeText(applicationContext, "Красавец, усложняем!", Toast.LENGTH_SHORT).show()
            updateQuestion()
            inputWord.clear()
        } else {
            Toast.makeText(applicationContext, "Попробуй ещё раз!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateQuestion() {
        currentQuestionNumber++
        if (words_dict.get(currentQuestionNumber) == null) {
            currentQuestionNumber = 1
        }
        findViewById<TextView>(R.id.questionTextView).setText(
            shuffleLettersInWord(
                words_dict.get(
                    currentQuestionNumber
                )
            )
        )
        if (currentQuestionNumber == words_dict.size) {
            Toast.makeText(applicationContext, "Ты прошёл все уровни!", Toast.LENGTH_LONG).show()
        }
    }
}