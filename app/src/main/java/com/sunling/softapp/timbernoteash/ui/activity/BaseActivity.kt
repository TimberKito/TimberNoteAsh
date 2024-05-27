package com.sunling.softapp.timbernoteash.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.ktx.immersionBar

abstract class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setActivityView())
        initView()
    }

    open fun initView() {
        initStatusBar()
    }

    private fun initStatusBar() {
        immersionBar {
            statusBarDarkFont(true)
        }
    }

    abstract fun setActivityView(): View

}