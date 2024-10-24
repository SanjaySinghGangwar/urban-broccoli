package dev.sanjaygangwar.tempproject.ui.fragment.home

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import dev.sanjaygangwar.tempproject.databinding.OptionsItemsBinding
import dev.sanjaygangwar.tempproject.models.dataclass.Items

class HomeAdapter(private val context: Context, private val listener: ItemListener,private val longListener: LongItemListener) :
    RecyclerView.Adapter<HomeViewHolder>() {


    private val items = ArrayList<Items>()
    private var lastPosition = -1
    val TAG = "Home Adapter "


    fun setItems(items: List<Items>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    interface ItemListener {
        fun onClicked(itemData: Items)
    }

    interface LongItemListener {
        fun onLongClicked(itemData: Items)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding: OptionsItemsBinding =
            OptionsItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(context, binding, listener)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(items[position])
        //setAnimation(holder.itemView, position);
    }


    override fun getItemCount(): Int {
        return items.size
    }

    private fun setAnimation(itemView: View, position: Int) {
        if (position > lastPosition) {
            val animation: Animation = AnimationUtils.loadAnimation(context, R.anim.slide_out_right)
            itemView.startAnimation(animation)
            lastPosition = position
        }

    }


}