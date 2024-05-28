package com.sunling.softapp.timbernoteash.ui.fragment

import android.content.Intent
import android.net.Uri
import android.view.View
import com.sunling.softapp.timbernoteash.App
import com.sunling.softapp.timbernoteash.R
import com.sunling.softapp.timbernoteash.databinding.FragmentInfoBinding
import com.sunling.softapp.timbernoteash.ui.activity.InfoActivity

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
                val intent = Intent(requireContext(), InfoActivity::class.java)
                startActivity(intent)
            }

            binding.infoShare -> {
                val url = getString(R.string.store_link) + App.appContext.packageName
                val intent = Intent(Intent.ACTION_SEND)
                intent.setType("text/plain")
                intent.putExtra(Intent.EXTRA_TEXT, url)
                startActivity(intent)
            }

            binding.infoFind -> {
                val url = getString(R.string.store_link) + App.appContext.packageName
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setData(Uri.parse(url))
                startActivity(intent)
            }
        }
    }

}