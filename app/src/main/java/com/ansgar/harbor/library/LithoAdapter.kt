package com.ansgar.harbor.library

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.ansgar.harbor.R
import com.facebook.litho.Column
import com.facebook.litho.ComponentContext
import com.facebook.litho.ComponentLayout
import com.facebook.litho.LithoView
import com.facebook.litho.Size
import com.facebook.litho.annotations.MountSpec
import com.facebook.litho.annotations.OnCreateMountContent
import com.facebook.litho.annotations.OnMeasure
import com.facebook.litho.utils.MeasureUtils
import com.facebook.litho.widget.Text
import com.facebook.yoga.YogaEdge
import kotlinx.android.extensions.LayoutContainer

data class LithoProfile(val name: String, val surName: String)

/**
 * To wrap a custom component. GifItemView.class will be generated after build
 */
@MountSpec
object GifItemViewSpec {
    @OnMeasure
    internal fun onMeasure(c: ComponentContext, layout: ComponentLayout, widthSpec: Int, heightSpec: Int, size: Size) {
        MeasureUtils.measureWithDesiredPx(widthSpec, heightSpec, 48 * 3, 48 * 3, size)
    }

    @OnCreateMountContent
    internal fun onCreateMountContent(c: Context): ImageView = ImageView(c).apply {
        setBackgroundColor(Color.WHITE)
        scaleType = ImageView.ScaleType.CENTER
        setImageResource(com.ansgar.harbor.R.mipmap.ic_launcher)
    }
}

class LithoAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = ArrayList<LithoProfile>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        val root = LayoutInflater.from(parent.context).inflate(viewType, null)
        val context = ComponentContext(parent.context)
        val root = Column.create(context)
            .child(Text.create(context)
                .text("Hello world")
                .textSizeDip(50f)
                .marginDip(YogaEdge.START, 80f))
//            .child(FrescoImage.create(context).build())
            .build()

        // The first frame won't be drawn neither we use LitheView as the root, or add into native ViewGroup.
        return LithoViewHolder(LithoView.create(context, root))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            (holder as? LithoViewHolder)?.onBind(items[position])
        }
    }

    override fun getItemCount() = items.count()

    override fun getItemViewType(position: Int): Int {
        return R.layout.listitem_litho
    }

    /**
     * Reset and set the new list.
     */
    fun setData(source: List<LithoProfile>) {
        items.apply {
            clear()
            addAll(source)
        }
        notifyDataSetChanged()
    }
}

class LithoViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun onBind(profile: LithoProfile) {
    }
}