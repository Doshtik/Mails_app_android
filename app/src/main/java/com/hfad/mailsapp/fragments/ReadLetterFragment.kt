package com.hfad.mailsapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.hfad.mailsapp.AppDatabase
import com.hfad.mailsapp.R
import com.hfad.mailsapp.dao.LetterDao
import com.hfad.mailsapp.dao.MailboxDao
import com.hfad.mailsapp.dao.RoleDao
import com.hfad.mailsapp.dao.UserDao
import com.hfad.mailsapp.databinding.FragmentReadLetterBinding
import com.hfad.mailsapp.databinding.FragmentRegistrationBinding
import com.hfad.mailsapp.view_models.ReadLetterViewModel
import com.hfad.mailsapp.view_models.RegistrationViewModel
import com.hfad.mailsapp.view_models.factories.ReadLetterViewModelFactory
import com.hfad.mailsapp.view_models.factories.RegistrationViewModelFactory

class ReadLetterFragment : Fragment() {
    private var _binding: FragmentReadLetterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentReadLetterBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val letterDao: LetterDao = AppDatabase.getInstance(application).letterDao()
        val mailboxDao: MailboxDao = AppDatabase.getInstance(application).mailboxDao()

        val viewModelFactory = ReadLetterViewModelFactory(letterDao, mailboxDao)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(ReadLetterViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner



        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}