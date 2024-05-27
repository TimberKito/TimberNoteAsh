package com.sunling.softapp.timbernoteash.ui.activity

import android.content.Intent
import android.view.View
import com.sunling.softapp.timbernoteash.databinding.ActivityStartBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StartActivity : BaseActivity() {

    private lateinit var binding: ActivityStartBinding
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private val countTime: Long = 2000

    override fun setActivityView(): View {
        binding = ActivityStartBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initView() {
        super.initView()
        coroutineScope.launch {
            delay(countTime)
            enterMainActivity()
        }
    }

    private fun enterMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}