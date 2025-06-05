package com.hfad.mailsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.hfad.mailsapp.AppDatabase
import com.hfad.mailsapp.R
import com.hfad.mailsapp.adapters.LetterItemAdapter
import com.hfad.mailsapp.dao.LetterDao
import com.hfad.mailsapp.dao.MailboxDao
import com.hfad.mailsapp.databinding.FragmentLetterMenuBinding
import com.hfad.mailsapp.view_models.LetterMenuViewModel
import com.hfad.mailsapp.view_models.factories.LetterMenuViewModelFactory

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

        viewModel.letters.observe(viewLifecycleOwner) { letters ->
            adapter.submitList(letters)
        }

        binding.buttonMakeLetter.setOnClickListener() {
            val action = LetterMenuFragmentDirections.actionFromLetterMenuToMakeLetter(viewModel.mailboxSenderId!!)
            view.findNavController().navigate(action)
        }

        binding.navView.setNavigationItemSelectedListener { menuItem ->
            viewModel.mailboxSenderId?.let { mailboxId ->
                when (menuItem.itemId) {
                    R.id.nav_inbox -> viewModel.loadInboxLetters(mailboxId)
                    R.id.nav_sent -> viewModel.loadSentLetters(mailboxId)
                    R.id.nav_favorite -> viewModel.loadFavoriteLetters(mailboxId)
                    R.id.nav_drafts -> viewModel.loadDraftLetters(mailboxId)
                    R.id.nav_trash -> viewModel.loadGarbageLetters(mailboxId)
                }
            }
            true
        }
        viewModel.mailboxSenderId?.let {
            viewModel.loadInboxLetters(it)
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