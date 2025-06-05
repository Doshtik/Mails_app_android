package com.hfad.mailsapp.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.hfad.mailsapp.AppDatabase
import com.hfad.mailsapp.adapters.MailboxItemAdapter
import com.hfad.mailsapp.dao.MailboxDao
import com.hfad.mailsapp.dao.UserDao
import com.hfad.mailsapp.databinding.FragmentSelectorMailboxBinding
import com.hfad.mailsapp.models.Mailbox
import com.hfad.mailsapp.view_models.SelectorMailboxesViewModel
import com.hfad.mailsapp.view_models.factories.SelectorMailboxesViewModelFactory
import kotlinx.coroutines.launch

class SelectorMailboxFragment : Fragment() {
    private var _binding: FragmentSelectorMailboxBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSelectorMailboxBinding.inflate(inflater, container, false)
        var view = binding.root

        val application = requireNotNull(this.activity).application
        val userDao: UserDao = AppDatabase.getInstance(application).userDao()
        val mailboxDao: MailboxDao = AppDatabase.getInstance(application).mailboxDao()

        val viewModelFactory = SelectorMailboxesViewModelFactory(userDao, mailboxDao)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(SelectorMailboxesViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.userId = SelectorMailboxFragmentArgs.fromBundle(requireArguments()).userId

        var selectedMailbox: Mailbox? = null
        val adapter = MailboxItemAdapter().apply {
            onItemSelected = { mailbox ->
                selectedMailbox = mailbox
            }
        }
        binding.mailboxRecyclerView.adapter = adapter

        binding.buttonConfirm.setOnClickListener() {
            if (adapter.selectedPosition != RecyclerView.NO_POSITION) {
                val action = SelectorMailboxFragmentDirections.actionFromSelectMailboxToLetterMenu(viewModel.mailboxId!!)
                view.findNavController().navigate(action)
            }
            else {
                showMessage(requireContext(), "Ошибка", "Вы не выбрали элемент")
            }
        }
        binding.buttonDelete.setOnClickListener() {
            if (adapter.selectedPosition != RecyclerView.NO_POSITION) {
                showDeleteMessage(requireContext(), "Подтверждение", "Удалить элемент?") { result ->
                    if (result) {
                        lifecycleScope.launch {
                            viewModel.deleteItem(selectedMailbox!!)
                        }
                    }
                }
            }
            else {
                showMessage(requireContext(), "Ошибка", "Вы не выбрали элемент")
            }
        }
        binding.buttonMakeMailbox.setOnClickListener() {
            val action = SelectorMailboxFragmentDirections.actionFromSelectMailboxToMakeMailbox(viewModel.userId!!)
            view.findNavController().navigate(action)
        }
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun showDeleteMessage(context: Context, title: String, message: String, callback: (Boolean) -> Unit){
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Да", {_, _ -> callback(true)})
            .setNegativeButton("Нет", {_, _ -> callback(false)})
            .create()
            .show()
    }
    private fun showMessage(context: Context, title: String, message: String) {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Ок", null)
            .create()
            .show()
    }
}