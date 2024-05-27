package com.sunling.softapp.timbernoteash.ui.activity

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.sunling.softapp.timbernoteash.R
import com.sunling.softapp.timbernoteash.databinding.ActivityMainBinding
import com.sunling.softapp.timbernoteash.databinding.ViewTabBinding
import com.sunling.softapp.timbernoteash.ui.fragment.AppInfoFragment
import com.sunling.softapp.timbernoteash.ui.fragment.CollectFragment
import com.sunling.softapp.timbernoteash.ui.fragment.NotesFragment


class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private var fragmentList: ArrayList<Fragment> = arrayListOf()

    override fun setActivityView(): View {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initView() {
        super.initView()
        initTabLayout()
        initViewPager()
    }

    private fun initViewPager() {

        val titles = arrayOf<String>(
            getString(R.string.tab_notes),
            getString(R.string.tab_collect),
            getString(R.string.tab_info)
        )

        binding.mainViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                binding.mainTab.getTabAt(position)?.select()
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })

        binding.mainViewPager.adapter = object : FragmentStatePagerAdapter(supportFragmentManager) {
            override fun getCount(): Int {
                return fragmentList.size
            }

            override fun getItem(position: Int): Fragment {
                return fragmentList[position]
            }

            override fun getPageTitle(position: Int): CharSequence {
                return titles[position]
            }

        }

    }

    private fun initTabLayout() {

        fragmentList.add(NotesFragment())
        fragmentList.add(CollectFragment())
        fragmentList.add(AppInfoFragment())

        val titles = arrayOf<String>(
            getString(R.string.tab_notes),
            getString(R.string.tab_collect),
            getString(R.string.tab_info)
        )

        for (i in 0 until fragmentList.size) {
            val newTab = binding.mainTab.newTab()
            val viewTabBinding = ViewTabBinding.inflate(layoutInflater)
            viewTabBinding.viewTabText.text = titles[i]
            newTab.customView = viewTabBinding.root
            binding.mainTab.addTab(newTab)
        }

        val tabAt = binding.mainTab.getTabAt(1)
        val tabAt2 = binding.mainTab.getTabAt(2)
        if (tabAt != null && tabAt2 != null) {
            updateTab(tabAt, false)
            updateTab(tabAt2, false)
        }

        binding.mainTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(p0: TabLayout.Tab?) {
                if (p0 != null) {
                    updateTab(p0, true)
                    binding.mainViewPager.setCurrentItem(p0.position)
                }
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
                p0?.let { updateTab(it, false) }
            }

            override fun onTabReselected(p0: TabLayout.Tab?) {

            }
        })
    }

    private fun updateTab(it: TabLayout.Tab, b: Boolean) {
        val customView = it.customView ?: return
        val textView = customView.findViewById<TextView>(R.id.view_tab_text)
        textView.isSelected = b
        if (b) {
            textView.background = ContextCompat.getDrawable(this@MainActivity, R.drawable.shape_yellow_r16)
        } else {
            textView.background = null
        }
    }

}