package dev.sanjaygangwar.tempproject.ui.fragment.home


import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import dev.sanjaygangwar.tempproject.R
import dev.sanjaygangwar.tempproject.databinding.OptionsItemsBinding
import dev.sanjaygangwar.tempproject.models.dataclass.Items
import dev.sanjaygangwar.tempproject.utils.extenstionfuntions.Extensions.loadImageFromUrl


class HomeViewHolder(
    private val context: Context,
    private val itemBinding: OptionsItemsBinding,
    private val listener: HomeAdapter.ItemListener,
) : RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener, View.OnLongClickListener {

    private lateinit var items: Items

    init {
        itemBinding.root.setOnClickListener(this)
        itemBinding.root.setOnLongClickListener(this)
    }


    fun bind(item: Items) {
        this.items = item
        itemBinding.name.text = this.items.name
        itemBinding.image.loadImageFromUrl(this.items.imageUrl)
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.root -> {
                listener.onClicked(items)
            }
        }

    }

    override fun onLongClick(v: View?): Boolean {
        when (v?.id) {
            R.id.root -> {
                listener.onClicked(items)
            }
        }
        return false
    }

}