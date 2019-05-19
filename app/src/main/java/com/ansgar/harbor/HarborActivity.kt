package com.ansgar.harbor

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ansgar.harbor.component.ComponentActivity
import com.ansgar.harbor.library.LithoActivity
import kotlinx.android.synthetic.main.activity_main.*

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
    }
}
