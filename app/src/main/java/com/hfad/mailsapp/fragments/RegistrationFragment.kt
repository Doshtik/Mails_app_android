package com.hfad.mailsapp.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.hfad.mailsapp.AppDatabase
import com.hfad.mailsapp.R
import com.hfad.mailsapp.dao.RoleDao
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
        val roleDao: RoleDao = AppDatabase.getInstance(application).roleDao()

        val viewModelFactory = RegistrationViewModelFactory(userDao, roleDao)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(RegistrationViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.buttonConfirm.setOnClickListener() {
            if(!viewModel.isUserExist())
            {
                viewModel.create()
                showMessage(requireContext(), "Успех", "Пользователь успешно зарегистрирован")
                view.findNavController()
                    .navigate(R.id.action_from_reg_to_auth)
            }
            else
            {
                showMessage(requireContext(), "Ошибка", "Пользователь уже существует")
            }
        }

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showMessage(context: Context, title: String, message: String) {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK", null)
            .create()
            .show()
    }
}