package mx.com.coppel.coppel_test.ui.profile_screen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import coil.load
import coil.transform.CircleCropTransformation
import mx.com.coppel.coppel_test.R
import mx.com.coppel.coppel_test.core.BaseViewHolder
import mx.com.coppel.coppel_test.data.model.Item
import mx.com.coppel.coppel_test.databinding.ItemCharacterBinding
import mx.com.coppel.coppel_test.databinding.ItemItemBinding

/*
 * @author azapata
 * Feb 2022
*/
class ItemsAdapter(
    private val context: Context,
    private val ItemList: List<Item>,
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemItemBinding =
            ItemItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ItemViewHolder(itemItemBinding)
    }

    override fun getItemCount() = ItemList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is ItemViewHolder -> {
                holder.bind(ItemList[position])
            }
        }
    }

    inner class ItemViewHolder(private val itemCharacterBinding: ItemItemBinding) :
        BaseViewHolder<Item>(itemCharacterBinding.root) {
        override fun bind(item: Item) {
            itemCharacterBinding.txtName.text = item.name
            itemCharacterBinding.imgCharacter.load(R.drawable.ic_placeholder_img) {
                crossfade(true)
                transformations(CircleCropTransformation())
            }
        }
    }
}