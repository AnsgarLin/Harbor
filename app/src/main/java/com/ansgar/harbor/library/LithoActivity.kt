package com.ansgar.harbor.library

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ansgar.harbor.R
import kotlinx.android.synthetic.main.activity_litho.*

class LithoActivity : AppCompatActivity() {
    private var lithoAdapter: LithoAdapter = LithoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_litho)

        list_litho.apply {
            layoutManager = LinearLayoutManager(this@LithoActivity)
            adapter = lithoAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        lithoAdapter.setData(listOf(
            LithoProfile("Ansgar", "Lin"),
            LithoProfile("Joan", "Feng"),
            LithoProfile("Jason", "Lee")))
    }
}

