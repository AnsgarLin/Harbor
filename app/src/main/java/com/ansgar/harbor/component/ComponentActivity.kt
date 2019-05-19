package com.ansgar.harbor.component

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ansgar.harbor.R
import com.ansgar.harbor.library.LithoAdapter
import com.ansgar.harbor.library.LithoProfile
import com.facebook.litho.ComponentContext
import kotlinx.android.synthetic.main.activity_litho.*

class ComponentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_component)
    }
}

