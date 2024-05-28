package com.sunling.softapp.timbernoteash.ui.activity

import android.content.pm.PackageManager
import android.view.View
import com.sunling.softapp.timbernoteash.databinding.ActivityInfoBinding

class InfoActivity : BaseActivity() {

    private lateinit var binding: ActivityInfoBinding

    override fun setActivityView(): View {
        binding = ActivityInfoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initView() {
        super.initView()
        binding.backBtn.setOnClickListener {
            finish()
        }
        binding.versionTv.text = "Version: " + getAppVersion(this)
    }

    private fun getAppVersion(infoActivity: InfoActivity): Any? {
        return try {
            val pInfo = infoActivity.packageManager.getPackageInfo(infoActivity.packageName, 0)
            pInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            "N/A"
        }
    }
}