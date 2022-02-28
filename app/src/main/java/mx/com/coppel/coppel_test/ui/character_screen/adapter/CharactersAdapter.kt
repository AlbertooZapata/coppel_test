package mx.com.coppel.coppel_test.ui.character_screen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import coil.load
import coil.transform.CircleCropTransformation
import mx.com.coppel.coppel_test.R
import mx.com.coppel.coppel_test.core.BaseViewHolder
import mx.com.coppel.coppel_test.data.model.Result
import mx.com.coppel.coppel_test.databinding.ItemCharacterBinding

/*
 * @author azapata
 * Feb 2022
*/
class CharactersAdapter(
    private val context: Context,
    private val characterList: List<Result>,
    private val itemClickListener: OnCharacterClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemCharacterBinding =
            ItemCharacterBinding.inflate(LayoutInflater.from(context), parent, false)
        val holder = CharacterViewHolder(itemCharacterBinding)
        itemCharacterBinding.root.setOnClickListener {
            val position =
                holder.adapterPosition.takeIf { it != NO_POSITION } ?: return@setOnClickListener
            itemClickListener.onItemClick(characterList[position])
        }
        return holder
    }

    override fun getItemCount() = characterList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {

        when (holder) {
            is CharacterViewHolder -> {
                holder.bind(characterList[position])
            }
        }
    }

    inner class CharacterViewHolder(private val itemCharacterBinding: ItemCharacterBinding) :
        BaseViewHolder<Result>(itemCharacterBinding.root) {
        override fun bind(item: Result) {
            itemCharacterBinding.txtName.text = item.name
            val fullImg = "${item.thumbnail.path}.${item.thumbnail.extension}"
            itemCharacterBinding.imgCharacter.load(fullImg) {
                crossfade(true)
                placeholder(R.drawable.ic_placeholder_img)
                transformations(CircleCropTransformation())
            }
        }
    }

    interface OnCharacterClickListener {
        fun onItemClick(result: Result)
    }
}