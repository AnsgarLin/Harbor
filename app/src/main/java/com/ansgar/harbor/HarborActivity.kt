package com.ansgar.harbor

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ansgar.harbor.HarborApplication.Companion.MAIN
import com.ansgar.harbor.component.ComponentActivity
import com.ansgar.harbor.library.LithoActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.RuntimeException

class HarborActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setEntries()
    }

    private fun setEntries() {
        entry_litho.setOnClickListener {
            startActivity(Intent(this, LithoActivity::class.java))
        }
        entry_component.setOnClickListener {
            startActivity(Intent(this, ComponentActivity::class.java))
        }
        entry_exception.setOnClickListener {
            throw IllegalStateException()
        }
    }
}
