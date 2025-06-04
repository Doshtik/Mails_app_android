package com.hfad.mailsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hfad.mailsapp.AppDatabase
import com.hfad.mailsapp.dao.UserDao
import com.hfad.mailsapp.databinding.FragmentRegistrationBinding
import com.hfad.mailsapp.view_models.RegistrationViewModel
import com.hfad.mailsapp.view_models.factories.RegistrationViewModelFactory

class RegistrationFragment : Fragment() {
    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val userDao: UserDao = AppDatabase.getInstance(application).userDao()

        val viewModelFactory = RegistrationViewModelFactory(userDao)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(RegistrationViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return view
    }
}