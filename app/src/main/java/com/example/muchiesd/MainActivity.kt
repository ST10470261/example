/*
*
*
*
* */

package com.example.muchiesd

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import com.example.dmunchies.MainActivity2
import com.example.dmunchies.MainActivity3
import com.example.dmunchies.MainActivity4
import com.example.dmunchies.MainActivity5
import com.example.dmunchies.MainActivity6
import com.example.dmunchies.MainActivity7
import kotlin.system.exitProcess





class MainActivity : AppCompatActivity() {


    private var viewMealBtn: Button? = null
    private var checkTimeBtn: Button? = null
    private var timeOfDayTxt: TextView? = null
    var timeInput: EditText? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //Initialising UI elements

        timeInput = findViewById(R.id.timeInputTxt)
        timeOfDayTxt = findViewById(R.id.timeOfDayTxt)
        checkTimeBtn = findViewById(R.id.checkTimeBtn)
        viewMealBtn = findViewById(R.id.viewMealBtn)
        val clearBtn = findViewById<Button>(R.id.clearBtn)
        val exitBtn = findViewById<Button>(R.id.exitBtn)


        checkTimeBtn?.setOnClickListener {
            checkTimeAndUpdateText()
        }

        viewMealBtn?.setOnClickListener {
            navigateToResultActivity()


        }

        clearBtn.setOnClickListener {
            timeInput?.text?.clear()
            timeOfDayTxt?.text = "" // Clear the time of day text
        }

        exitBtn.setOnClickListener {
            finishAffinity()
            exitProcess(0)
        }
    }

    private fun checkTimeAndUpdateText() { // Renamed for clarity
        val timeString = timeInput?.text.toString().trim()
        if (timeString.isEmpty()) {
            timeInput?.error = "Time Required"
            return
        }
        val time = timeString.toIntOrNull()
        if (time == null || time !in 0..2359) {
            timeInput?.error = "Invalid Time (e.g: 1700)"
            return
        }

        val timeOfDay = when (time) {
            in 600..959 -> "Morning"
            in 1000..1159 -> "Mid Morning"
            in 1200..1359 -> "Afternoon"
            in 1400..1659 -> "Mid Afternoon"
            in 1700..1959 -> "Evening"
            in 2000..2359 -> "Night"
            in 0..599 -> "Bedtime (SWEET DREAMS ;) )"
            else -> "Invalid Time Range " // Covers 0-559
        }
        timeOfDayTxt?.text = "Time of day: $timeOfDay"
    }

    private fun navigateToResultActivity() {
        val timeOfDay = timeOfDayTxt?.text.toString()
        val intent = when (timeOfDay) {
            "Time of day: Morning" -> Intent(this, MainActivity2::class.java)
            "Time of day: Mid Morning" -> Intent(this, MainActivity3::class.java) // Corrected
            "Time of day: Afternoon" -> Intent(this, MainActivity4::class.java)
            "Time of day: Mid Afternoon" -> Intent(this, MainActivity5::class.java)
            "Time of day: Evening" -> Intent(this, MainActivity6::class.java)
            "Time of day: Night" -> Intent(this, MainActivity7::class.java)
            else -> null // Handle invalid or missing time of day
        }

        if (intent != null) {
            startActivity(intent)
        } else {
            Toast.makeText(
                this, "Please enter a valid time first (600=6:00 AM to 2359=11:59 PM)",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}