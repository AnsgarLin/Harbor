package com.ansgar.harbor.component

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_text.view.*
import kotlinx.android.synthetic.main.listitem_chat.*

data class ComponentType(val name: String, @LayoutRes val id: Int)

class ComponentAdapter : RecyclerView.Adapter<ComponentViewHolder>() {
    private val viewTypeSet = arrayListOf<ComponentType>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComponentViewHolder {
        return ComponentViewHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))
    }

    override fun onBindViewHolder(holder: ComponentViewHolder, position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            holder.onBind(viewTypeSet[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return viewTypeSet[position].id
    }

    override fun getItemCount() = viewTypeSet.count()

    /**
     * Reset all types
     */
    fun setViewTypeSets(typeSet: List<ComponentType>) {
        viewTypeSet.apply {
            clear()
            viewTypeSet.addAll(typeSet)
        }
        notifyDataSetChanged()
    }
}

class ComponentViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun onBind(type: ComponentType) {
        chat_header.text = "abcd efg hijkabcd efg hijkabcd efg hijkabcd efg hijkabcd efg hijkabcd efg hijkabcd efg hijkabcd efg hijkabcd efg hijkabcd efg hijkabcd efg hijk"
        chat_header.text2 = "3mins"
    }
}