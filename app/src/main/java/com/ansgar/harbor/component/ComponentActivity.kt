package com.ansgar.harbor.component

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ansgar.harbor.R
import kotlinx.android.synthetic.main.activity_component.*

class ComponentActivity : AppCompatActivity() {
    private val componentAdapter = ComponentAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_component)

        list_component.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = componentAdapter
        }
    }

    override fun onResume() {
        super.onResume()

        componentAdapter.setViewTypeSets(
            listOf(
                ComponentType("listitem_chat", R.layout.listitem_chat)
            )
        )
    }
}

