package com.ansgar.harbor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.facebook.litho.ComponentContext
import com.facebook.litho.LithoView
import com.facebook.litho.widget.Text

class HarborActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        val context = ComponentContext(this)

        val component = Text.create(context)
            .text("Hello world")
            .textSizeDip(50f)
            .build()

        setContentView(LithoView.create(context, component))
    }
}
