package com.sunling.softapp.timbernoteash.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.sunling.softapp.timbernoteash.databinding.FragmentCollectBinding
import com.sunling.softapp.timbernoteash.db.DataBaseManager
import com.sunling.softapp.timbernoteash.entity.Note
import com.sunling.softapp.timbernoteash.tools.AppConstString
import com.sunling.softapp.timbernoteash.tools.AppConstString.EXTRA_KEY
import com.sunling.softapp.timbernoteash.tools.SpacingItemDecoration
import com.sunling.softapp.timbernoteash.ui.activity.NoteEditActivity
import com.sunling.softapp.timbernoteash.ui.adapter.CollectAdapter
import com.sunling.softapp.timbernoteash.ui.listener.ClickActionListener

class CollectFragment : BaseFragment() {

    private lateinit var binding: FragmentCollectBinding
    private lateinit var collectAdapter: CollectAdapter
    private lateinit var mHandler: Handler
    private var allCollectList: MutableList<Note> = mutableListOf()

    override fun setFragmentView(): View {
        binding = FragmentCollectBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initView() {
        super.initView()
        Thread {
            allCollectList.addAll(DataBaseManager.notesDatabase.noteDao().getCollectData(true))
            mHandler.sendEmptyMessage(0)
        }.start()
        initItem()
    }

    private fun initItem() {
        collectAdapter = CollectAdapter(requireActivity(), object : ClickActionListener {
            override fun clickAction(i: Int, note: Note?) {
                note?.let {
                    startActivity(Intent(requireActivity(), NoteEditActivity::class.java).apply {
                        putExtra(EXTRA_KEY, note)
                        putExtra(AppConstString.EDIT_TYPE_KEY, 1)
                    })
                }
            }

            override fun colorsSelect(i: Int) {
            }
        })

        binding.collectRecyclerView.apply {
            adapter = collectAdapter
            addItemDecoration(SpacingItemDecoration(6))
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val collectNotesList: MutableList<Note> = mutableListOf()
        mHandler = Handler {
            collectAdapter.updateData(collectNotesList)
            false
        }
//        binding.collectEmpty.isVisible = allCollectList.isEmpty()

        Thread {
            collectNotesList.addAll(DataBaseManager.notesDatabase.noteDao().getCollectData(true))
            mHandler.sendEmptyMessage(0)
        }.start()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mHandler = Handler {
            collectAdapter.updateData(allCollectList)
            false
        }
    }

}