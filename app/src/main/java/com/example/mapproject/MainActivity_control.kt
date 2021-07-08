package com.example.mapproject

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import io.realm.Sort
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.startActivity

class MainActivity_control : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_control)


        title = "운동 기록"

        // 지도로 이동
        val btnMap = findViewById<Button>(R.id.Map)
        btnMap.setOnClickListener {
            val intent = Intent(applicationContext, MapsActivity::class.java)
            startActivity(intent)
        }

        // 운동 일지로 이동
        val btntodo = findViewById<Button>(R.id.todo)
        btntodo.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

        // 타이머로 이동
        val btntimer = findViewById<Button>(R.id.timer)
        btntimer.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity_timer::class.java)
            startActivity(intent)
        }
    }
}
