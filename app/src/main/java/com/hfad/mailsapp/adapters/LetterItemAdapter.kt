package com.hfad.mailsapp.adapters

import android.graphics.Color
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hfad.mailsapp.fragments.LetterMenuFragmentDirections
import com.hfad.mailsapp.models.Letter
import com.hfad.mailsapp.view_models.LetterItemViewHolder

class LetterItemAdapter(private val onItemClick: (Letter) -> Unit): ListAdapter<Letter, LetterItemViewHolder>(LetterDiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : LetterItemViewHolder {
        return LetterItemViewHolder.inflateFrom(parent)
    }

    //Вызывается, когда данные должны отображаться в держателе проедставления.
    override fun onBindViewHolder(holder: LetterItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)

        holder.itemView.setOnClickListener {
            holder.itemView.setOnClickListener {
                onItemClick(item) // Вызов колбэка вместо прямой навигации
            }
        }
    }
}
class LetterDiffItemCallback : DiffUtil.ItemCallback<Letter>() {
    override fun areItemsTheSame(oldItem: Letter, newItem: Letter): Boolean {
        return (oldItem.Id == newItem.Id)
    }

    override fun areContentsTheSame(oldItem: Letter, newItem: Letter): Boolean {
        return oldItem.Id == newItem.Id &&
            oldItem.IdMilboxSender == newItem.IdMilboxSender &&
            oldItem.IdMilboxRecipient == newItem.IdMilboxRecipient
            oldItem.IdCopyRecipient == newItem.IdCopyRecipient
    }
}