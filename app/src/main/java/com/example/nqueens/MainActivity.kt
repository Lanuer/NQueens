package com.example.nqueens

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import java.lang.StringBuilder
import java.text.FieldPosition

class MainActivity : Activity() {

    private lateinit var tv: TextView

    private var totalQueens = 0
    private lateinit var queens: IntArray
    private lateinit var solutions: MutableList<List<String>>
    private lateinit var sb: StringBuilder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById()
        setListener()
        init()
    }


    fun findViewById() {
        tv = findViewById(R.id.tv)
    }

    fun setListener() {

    }

    fun init() {
        sb = StringBuilder()
        for (a in 0 until solutionsQueens().size) {
            sb.append("Solution " + (a+1) + "\n" + solutionsQueens()[a].toString().replace("[", "").replace("]", "").replace(",", "\n") + "\n\n")
        }
        tv.text = sb.toString()
    }

    fun solutionsQueens(): List<List<String>> {
        solutions = mutableListOf()
        totalQueens = 8
        queens = IntArray(8)
        backTrack(0)
        return solutions
    }

    private fun backTrack(current: Int) {
        if (current == totalQueens) {
            val queenList = mutableListOf<String>()
            for (i in 0 until totalQueens) {
                val stringBuilder = StringBuilder()
                for (j in 0 until totalQueens) {
                    stringBuilder.append(if (queens[i] == j) "Q" else ".")
                }
                queenList.add(stringBuilder.toString())
            }
            solutions.add(queenList)
            return
        }

        for (position in 0 until totalQueens) {
            queens[current] = position
            if (canPlace(current)) backTrack(current + 1)
        }
    }

    private fun canPlace(position: Int): Boolean {
        for (before in 0 until position) {
            if (queens[before] == queens[position] || Math.abs(queens[position] - queens[before]) == Math.abs(position - before))
            return false
        }
        return true
    }
}