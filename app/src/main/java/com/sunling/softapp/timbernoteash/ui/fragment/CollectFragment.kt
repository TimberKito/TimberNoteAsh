package com.sunling.softapp.timbernoteash.ui.fragment

import android.view.View
import com.sunling.softapp.timbernoteash.databinding.FragmentCollectBinding

class CollectFragment : BaseFragment() {

    private lateinit var binding: FragmentCollectBinding

    override fun setFragmentView(): View {
        binding = FragmentCollectBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initView() {
        super.initView()
    }
}