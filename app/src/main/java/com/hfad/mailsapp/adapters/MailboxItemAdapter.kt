package com.hfad.mailsapp.adapters

import android.graphics.Color
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hfad.mailsapp.models.Mailbox
import com.hfad.mailsapp.view_models.MailboxItemViewHolder

class MailboxItemAdapter : ListAdapter<Mailbox, MailboxItemViewHolder>(MailDiffItemCallback()) {

    // Текущая выбранная позиция
    var selectedPosition = RecyclerView.NO_POSITION
    // Callback для обработки выбора элемента
    var onItemSelected: ((Mailbox) -> Unit)? = null

    // Цвета для состояний
    private var selectedColor = Color.LTGRAY
    private var defaultColor = Color.WHITE

    //Вызывается каждый раз, когда требуется создать держатель представления.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : MailboxItemViewHolder {
        return MailboxItemViewHolder.inflateFrom(parent)
    }

    //Вызывается, когда данные должны отображаться в держателе проедставления.
    override fun onBindViewHolder(viewHolder: MailboxItemViewHolder, position: Int) {
        val item = getItem(position)
        viewHolder.bind(item)

        // Устанавливаем цвет фона в зависимости от выбранной позиции
        viewHolder.itemView.setBackgroundColor(
            if (position == selectedPosition) { selectedColor }
            else { defaultColor }
        )

        viewHolder.itemView.setOnClickListener {
            // Обновляем предыдущую выбранную позицию
            val previousSelected = selectedPosition
            // Устанавливаем новую выбранную позицию
            selectedPosition = viewHolder.adapterPosition

            // Уведомляем об изменении предыдущего элемента
            if (previousSelected != RecyclerView.NO_POSITION) {
                notifyItemChanged(previousSelected)
            }
            // Уведомляем об изменении нового элемента
            notifyItemChanged(selectedPosition)
            // Дополнительные действия при выборе элемента
            onItemSelected?.invoke(item)
        }
    }

}
class MailDiffItemCallback : DiffUtil.ItemCallback<Mailbox>() {
    override fun areItemsTheSame(oldItem: Mailbox, newItem: Mailbox): Boolean {
        return (oldItem.Id == newItem.Id)
    }

    override fun areContentsTheSame(oldItem: Mailbox, newItem: Mailbox): Boolean {
        return oldItem.Id == newItem.Id &&
                oldItem.IdUser == newItem.IdUser &&
                oldItem.MailName == newItem.MailName
    }
}