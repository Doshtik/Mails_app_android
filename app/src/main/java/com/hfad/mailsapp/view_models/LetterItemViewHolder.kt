package com.hfad.mailsapp.view_models

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hfad.mailsapp.databinding.LetterItemBinding
import com.hfad.mailsapp.databinding.MailboxItemBinding
import com.hfad.mailsapp.models.Letter
import com.hfad.mailsapp.models.Mailbox

class LetterItemViewHolder(val binding: LetterItemBinding) : RecyclerView.ViewHolder(binding.root){
    companion object {
        fun inflateFrom(parent: ViewGroup): LetterItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = LetterItemBinding.inflate(layoutInflater, parent, false)
            return LetterItemViewHolder(binding)
        }
    }
    fun bind(letter: Letter) {
        binding.letterIsFavorite.isChecked = letter.IsFavorite
        binding.letterTheme.text = letter.Theme
        binding.letterMessage.text = letter.Message
        binding.letterDate.text = letter.Date.toString()
        binding.executePendingBindings()
    }
}