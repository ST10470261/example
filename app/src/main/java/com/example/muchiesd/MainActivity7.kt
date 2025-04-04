package com.example.dmunchies

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.muchiesd.MainActivity

class MainActivity7 : AppCompatActivity() {


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main7)
            val backButton: Button? = findViewById(R.id.backBtn)
            val proceedButton: Button? = findViewById(R.id.proceedBtn)

            backButton?.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            }

            proceedButton?.setOnClickListener {
                Toast.makeText(this, "Your meal shall be prepared shortly", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            }
        }
    }
