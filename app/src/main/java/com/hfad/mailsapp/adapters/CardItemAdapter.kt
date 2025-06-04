package com.hfad.mailsapp.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hfad.mailsapp.databinding.MailboxItemBinding
import com.hfad.mailsapp.models.Mailbox

class CardItemAdapter : ListAdapter<Mailbox, CardItemAdapter.CardItemViewHolder>(CardDiffItemCallback()) {

    // Текущая выбранная позиция
    var selectedPosition = RecyclerView.NO_POSITION

    // Цвета для состояний
    private var selectedColor = Color.LTGRAY
    private var defaultColor = Color.WHITE

    //Вызывается каждый раз, когда требуется создать держатель представления.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : CardItemViewHolder = CardItemViewHolder.inflateFrom(parent)

    //Вызывается, когда данные должны отображаться в держателе проедставления.
    override fun onBindViewHolder(holder: CardItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)

        // Устанавливаем цвет фона в зависимости от выбранной позиции
        holder.itemView.setBackgroundColor(
            if (position == selectedPosition) selectedColor else defaultColor
        )

        holder.itemView.setOnClickListener {
            // Обновляем предыдущую выбранную позицию
            val previousSelected = selectedPosition
            // Устанавливаем новую выбранную позицию
            selectedPosition = holder.adapterPosition

            // Уведомляем об изменении предыдущего элемента
            if (previousSelected != RecyclerView.NO_POSITION) {
                notifyItemChanged(previousSelected)
            }
            // Уведомляем об изменении нового элемента
            notifyItemChanged(selectedPosition)
        }
    }
    class CardItemViewHolder(val binding: MailboxItemBinding) : RecyclerView.ViewHolder(binding.root){
        companion object {
            //Создает каждый держатель представления и заполняет его макет.
            fun inflateFrom(parent: ViewGroup): CardItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MailboxItemBinding.inflate(layoutInflater, parent, false)
                return CardItemViewHolder(binding)
            }
        }
        //Данные добавляются в макет держателя представления.
        fun bind(mailbox: Mailbox) {
            binding.mailboxName.text = mailbox.MailName
            binding.executePendingBindings()
        }
    }
}
class CardDiffItemCallback : DiffUtil.ItemCallback<Mailbox>() {
    override fun areItemsTheSame(oldItem: Mailbox, newItem: Mailbox): Boolean {
        return (oldItem.Id == newItem.Id)
    }

    override fun areContentsTheSame(oldItem: Mailbox, newItem: Mailbox): Boolean {
        return oldItem.Id == newItem.Id &&
                oldItem.IdUser == newItem.IdUser &&
                oldItem.MailName == newItem.MailName
    }
}