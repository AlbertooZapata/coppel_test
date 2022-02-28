package mx.com.coppel.coppel_test.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/*
 * @author azapata
 * Feb 2022
*/
abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}