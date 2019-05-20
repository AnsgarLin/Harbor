package com.ansgar.harbor.component

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.PrecomputedTextCompat
import androidx.core.widget.TextViewCompat
import com.ansgar.harbor.R
import kotlinx.android.synthetic.main.activity_text.*

class TextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        text.setTextFuture(
            PrecomputedTextCompat.getTextFuture(
                getString(R.string.sample_long_string),
                TextViewCompat.getTextMetricsParams(text),
                null
            )
        )
    }
}