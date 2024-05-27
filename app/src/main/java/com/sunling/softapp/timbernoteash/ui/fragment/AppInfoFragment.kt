package com.sunling.softapp.timbernoteash.ui.fragment

import android.view.View
import com.sunling.softapp.timbernoteash.databinding.FragmentInfoBinding

class AppInfoFragment : BaseFragment(), View.OnClickListener {

    private lateinit var binding: FragmentInfoBinding

    override fun setFragmentView(): View {
        binding = FragmentInfoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initView() {
        super.initView()
        initButton()

    }

    private fun initButton() {
        binding.infoApp.setOnClickListener(this)
        binding.infoShare.setOnClickListener(this)
        binding.infoFind.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.infoApp -> {

            }

            binding.infoShare -> {

            }

            binding.infoFind -> {

            }
        }
    }


}