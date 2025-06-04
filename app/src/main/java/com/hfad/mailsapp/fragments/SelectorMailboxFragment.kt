package com.hfad.mailsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hfad.mailsapp.AppDatabase
import com.hfad.mailsapp.dao.MailboxDao
import com.hfad.mailsapp.dao.UserDao
import com.hfad.mailsapp.databinding.FragmentSelectorMailboxBinding
import com.hfad.mailsapp.models.Mailbox
import com.hfad.mailsapp.view_models.SelectorMailboxesViewModel
import com.hfad.mailsapp.view_models.factories.SelectorMailboxesViewModelFactory

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

        var mailboxes = viewModel.getByUserId();


        binding.buttonConfirm.setOnClickListener() {

        }
        binding.buttonDelete.setOnClickListener() {

        }
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}