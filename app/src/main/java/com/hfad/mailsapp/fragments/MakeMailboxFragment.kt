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
import com.hfad.mailsapp.dao.MailboxDao
import com.hfad.mailsapp.dao.UserDao
import com.hfad.mailsapp.databinding.FragmentAuthorizationBinding
import com.hfad.mailsapp.databinding.FragmentMakeMailboxBinding
import com.hfad.mailsapp.view_models.AuthorizationViewModel
import com.hfad.mailsapp.view_models.MakeMailboxViewModel
import com.hfad.mailsapp.view_models.factories.AuthorizationViewModelFactory
import com.hfad.mailsapp.view_models.factories.MakeMailboxViewModelFactory

class MakeMailboxFragment : Fragment() {
    private var _binding:  FragmentMakeMailboxBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMakeMailboxBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val mailboxDao: MailboxDao = AppDatabase.getInstance(application).mailboxDao()

        val viewModelFactory = MakeMailboxViewModelFactory(mailboxDao)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(MakeMailboxViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.buttonMakeMailbox.setOnClickListener() {
            if (viewModel.isMailNameAreTaken())
            {
                viewModel.create()
                val action = MakeLetterFragmentDirections.actionFromMakeLetterToLetterMenu(viewModel.userId!!)
                view.findNavController().navigate(action)
            }
        }
        return view
    }
}