package com.sunling.softapp.timbernoteash.ui.fragment

import android.view.View
import com.sunling.softapp.timbernoteash.databinding.FragmentNotesBinding

class NotesFragment : BaseFragment() {

    private lateinit var binding: FragmentNotesBinding

    override fun setFragmentView(): View {
        binding = FragmentNotesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initView() {
        super.initView()

    }
}