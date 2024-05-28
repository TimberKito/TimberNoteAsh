package com.sunling.softapp.timbernoteash.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sunling.softapp.timbernoteash.databinding.FragmentNotesBinding
import com.sunling.softapp.timbernoteash.db.DataBaseManager
import com.sunling.softapp.timbernoteash.entity.Note
import com.sunling.softapp.timbernoteash.tools.AppConstString.EDIT_TYPE_KEY
import com.sunling.softapp.timbernoteash.tools.AppConstString.EXTRA_KEY
import com.sunling.softapp.timbernoteash.tools.SpacingItemDecoration
import com.sunling.softapp.timbernoteash.ui.activity.NoteEditActivity
import com.sunling.softapp.timbernoteash.ui.adapter.NotesAdapter
import com.sunling.softapp.timbernoteash.ui.listener.ClickActionListener

class NotesFragment : BaseFragment() {

    private lateinit var binding: FragmentNotesBinding
    private var allNotesList: MutableList<Note> = mutableListOf()
    private lateinit var mAdapter: NotesAdapter
    private lateinit var mHandler: Handler

    override fun setFragmentView(): View {
        binding = FragmentNotesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initView() {
        super.initView()

        mAdapter = NotesAdapter(requireActivity(), object : ClickActionListener {
            override fun clickAction(i: Int, note: Note?) {
                if (i == 0) {
                    action(0, null)
                } else {
                    action(1, note)
                }
            }

            override fun colorsSelect(i: Int) {
            }

        })

        binding.notesRecyclerView.apply {
            adapter = mAdapter
            addItemDecoration(SpacingItemDecoration(6))
//            layoutManager = LinearLayoutManager(context).apply {
//                orientation = LinearLayoutManager.VERTICAL
//            }
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }

    }

    override fun onResume() {
        super.onResume()

        val allNotesList: MutableList<Note> = mutableListOf()
        mHandler = Handler {
            mAdapter.updateData(allNotesList)
            false
        }

        Thread {
            allNotesList.addAll(DataBaseManager.notesDatabase.noteDao().getAllNotes())
            mHandler.sendEmptyMessage(0)
        }.start()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mHandler = Handler {
            mAdapter.updateData(allNotesList)
            false
        }

        Thread {
            allNotesList.addAll(DataBaseManager.notesDatabase.noteDao().getAllNotes())
            mHandler.sendEmptyMessage(0)
        }.start()

    }

    private fun action(type: Int, note: Note?) {
        when (type) {
            0 -> {
                startActivity(Intent(requireActivity(), NoteEditActivity::class.java).apply {
                    putExtra(EDIT_TYPE_KEY, 0)
                })
            }

            1 -> {
                note?.let {
                    startActivity(Intent(requireActivity(), NoteEditActivity::class.java).apply {
                        putExtra(EXTRA_KEY, note)
                        putExtra(EDIT_TYPE_KEY, 1)
                    })
                }
            }
        }

    }
}