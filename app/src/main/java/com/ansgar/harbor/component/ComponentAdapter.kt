package com.ansgar.harbor.component

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.view.LayoutInflaterCompat
import androidx.recyclerview.widget.RecyclerView

data class ComponentType(val name: String, @LayoutRes val id: Int)

class ComponentAdapter : RecyclerView.Adapter<ComponentViewHolder>() {
    private val viewTypeSet = arrayListOf<ComponentType>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComponentViewHolder {
        return ComponentViewHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))
    }

    override fun onBindViewHolder(holder: ComponentViewHolder, position: Int) {
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

class ComponentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)