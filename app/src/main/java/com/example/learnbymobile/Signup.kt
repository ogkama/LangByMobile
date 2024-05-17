package com.example.learnbymobile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Signup : AppCompatActivity() {
    lateinit var imgView: ImageView
    lateinit var textView:TextView
    lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        imgView = findViewById(R.id.back) as ImageView
        imgView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val intent = Intent(this@Signup,LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
        textView = findViewById(R.id.textLogin) as TextView
        textView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val intent = Intent(this@Signup,LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
        button = findViewById(R.id.button2) as Button
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val intent = Intent(this@Signup,Confirm::class.java)
                startActivity(intent)
                finish()
            }
        })
        //val intent = Intent(this, LoginActivity::class.java)
        //startActivity(intent)
    }
}