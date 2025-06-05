package com.hfad.mailsapp.view_models

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hfad.mailsapp.AppDatabase
import com.hfad.mailsapp.dao.MailboxDao
import com.hfad.mailsapp.databinding.MailboxItemBinding
import com.hfad.mailsapp.models.Mailbox

class MailboxItemViewHolder(val binding: MailboxItemBinding) : RecyclerView.ViewHolder(binding.root){
    companion object {
        //Создает каждый держатель представления и заполняет его макет.
        fun inflateFrom(parent: ViewGroup): MailboxItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = MailboxItemBinding.inflate(layoutInflater, parent, false)
            return MailboxItemViewHolder(binding)
        }
    }
    //Данные добавляются в макет держателя представления.
    fun bind(mailbox: Mailbox) {
        binding.mailboxName.text = mailbox.MailName
        binding.executePendingBindings()
    }
}