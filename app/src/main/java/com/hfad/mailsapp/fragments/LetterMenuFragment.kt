package com.hfad.mailsapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hfad.mailsapp.AppDatabase
import com.hfad.mailsapp.R
import com.hfad.mailsapp.adapters.LetterItemAdapter
import com.hfad.mailsapp.adapters.MailboxItemAdapter
import com.hfad.mailsapp.dao.LetterDao
import com.hfad.mailsapp.dao.MailboxDao
import com.hfad.mailsapp.dao.RoleDao
import com.hfad.mailsapp.dao.UserDao
import com.hfad.mailsapp.databinding.FragmentLetterMenuBinding
import com.hfad.mailsapp.databinding.FragmentRegistrationBinding
import com.hfad.mailsapp.models.Letter
import com.hfad.mailsapp.models.Mailbox
import com.hfad.mailsapp.view_models.LetterMenuViewModel
import com.hfad.mailsapp.view_models.RegistrationViewModel
import com.hfad.mailsapp.view_models.factories.LetterMenuViewModelFactory
import com.hfad.mailsapp.view_models.factories.RegistrationViewModelFactory

class LetterMenuFragment : Fragment() {
    private var _binding: FragmentLetterMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLetterMenuBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val letterDao: LetterDao = AppDatabase.getInstance(application).letterDao()
        val mailboxDao: MailboxDao = AppDatabase.getInstance(application).mailboxDao()

        val viewModelFactory = LetterMenuViewModelFactory(letterDao, mailboxDao)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(LetterMenuViewModel::class.java)

        viewModel.mailboxSenderId = LetterMenuFragmentArgs.fromBundle(requireArguments()).mailboxId
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = LetterItemAdapter({ letter -> navigateToReadLetter(letter.Id)})
        binding.mailRecyclerView.adapter = adapter

        binding.buttonMakeLetter.setOnClickListener() {
            val action = LetterMenuFragmentDirections.actionFromLetterMenuToMakeLetter(viewModel.mailboxSenderId!!)
            view.findNavController().navigate(action)
        }
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun navigateToReadLetter(letterId: Int) {
        val action = LetterMenuFragmentDirections.actionFromLetterMenuToReadLetter(letterId)
        findNavController().navigate(action)
    }
}