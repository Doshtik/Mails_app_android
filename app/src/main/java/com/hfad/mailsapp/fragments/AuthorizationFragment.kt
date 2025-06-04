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
import com.hfad.mailsapp.dao.UserDao
import com.hfad.mailsapp.databinding.FragmentAuthorizationBinding
import com.hfad.mailsapp.view_models.AuthorizationViewModel
import com.hfad.mailsapp.view_models.factories.AuthorizationViewModelFactory

class AuthorizationFragment : Fragment() {
    private var _binding: FragmentAuthorizationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAuthorizationBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val userDao: UserDao = AppDatabase.getInstance(application).userDao()

        val viewModelFactory = AuthorizationViewModelFactory(userDao)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(AuthorizationViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.buttonConfirm.setOnClickListener() {
            if (viewModel.isUserAvailable())
            {
                val action = AuthorizationFragmentDirections.actionFromAuthToSelectMailbox(viewModel.userId)
                view.findNavController().navigate(action)
            }
            else {
                showMessage(requireContext(), "Ошибка", "Пользователь не найден")
            }

        }
        binding.linkMakeAccount.setOnClickListener() {
            view.findNavController()
                .navigate(R.id.action_from_auth_to_reg)
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