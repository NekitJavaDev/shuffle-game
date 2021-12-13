package ru.nekit.shuffle_game

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment

class MyDialogFragment : AppCompatDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it,R.style.Theme_MaterialComponents_Dialog_Bridge)
            builder.setTitle("Правила игры!")
                .setMessage("Правила игры!\n" +
                        "2ая строчка\n" +
                        "3я длинннннннннннннннннннннннаааая")
//                .setIcon(R.drawable.hungrycat)
                .setNegativeButton("Понятно") { dialog, id ->
                    dialog.cancel()
                }
                .setNeutralButton("Нейтрал") { dialog, id ->
                    dialog.cancel()
                }
                .setCancelable(true)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}