package com.sunling.softapp.timbernoteash.ui.activity

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.sunling.softapp.timbernoteash.R
import com.sunling.softapp.timbernoteash.databinding.ActivityEditBinding
import com.sunling.softapp.timbernoteash.db.DataBaseManager
import com.sunling.softapp.timbernoteash.entity.Note
import com.sunling.softapp.timbernoteash.tools.AppConstString.EDIT_TYPE_KEY
import com.sunling.softapp.timbernoteash.tools.AppConstString.EXTRA_KEY
import com.sunling.softapp.timbernoteash.tools.SpacingItemDecoration
import com.sunling.softapp.timbernoteash.ui.adapter.ColorsAdapter
import com.sunling.softapp.timbernoteash.ui.listener.ClickActionListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteEditActivity : BaseActivity(), View.OnClickListener {

    private lateinit var binding: ActivityEditBinding
    private var isCollect: Boolean = false
    private var note: Note? = null
    private var notetype: Int = 0
    private var selectedColor = 0
    private val notesBgColor = intArrayOf(
        R.drawable.shape_notes_00,
        R.drawable.shape_notes_01,
        R.drawable.shape_notes_02,
        R.drawable.shape_notes_03,
        R.drawable.shape_notes_04,
        R.drawable.shape_notes_05,
        R.drawable.shape_notes_06
    )

    override fun setActivityView(): View {
        binding = ActivityEditBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initView() {
        super.initView()

        note = intent.getSerializableExtra(EXTRA_KEY) as Note?

        notetype = intent.getIntExtra(EDIT_TYPE_KEY, 0)
        initNotesPage()
        initButton()
        initColorsSelect()
    }

    private fun initColorsSelect() {
        binding.recyclerColors.apply {
            addItemDecoration(SpacingItemDecoration(6))
            val mLayoutManager = LinearLayoutManager(applicationContext)
            mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL)
            layoutManager = mLayoutManager
            val mAdapter = ColorsAdapter(this@NoteEditActivity, object : ClickActionListener {
                override fun clickAction(i: Int, note: Note?) {
                }

                override fun colorsSelect(i: Int) {
                    selectedColor = i
                    binding.editLayout.background = applicationContext.getDrawable(notesBgColor[selectedColor])
                }
            })
            mAdapter.setData(notesBgColor)
            adapter = mAdapter
        }
    }

    private fun initButton() {
        binding.editBack.setOnClickListener(this)
        binding.editDelete.setOnClickListener(this)
        binding.editConfirm.setOnClickListener(this)
        // collect
        binding.editCollect.setOnClickListener(this)
    }

    private fun initNotesPage() {
        if (note != null) {
            binding.editUserTitle.setText(note!!.title)
            binding.editContent.setText(note!!.content)
            binding.editLayout.background = applicationContext.getDrawable(notesBgColor[note!!.color])
            binding.editCollect.isSelected = note!!.collect
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.editBack -> {
                finish()
            }

            binding.editDelete -> {
                if (notetype != 0) {
                    if (note != null) {
                        DataBaseManager.deleteNote(note!!)
                        Toast.makeText(this, "You have successfully deleted this note!", Toast.LENGTH_SHORT).show()
                    }
                }
                finish()
            }

            binding.editConfirm -> {
                val newTitle = binding.editUserTitle.text.toString()
                val newContent = binding.editContent.text.toString()
                val newCollect = binding.editCollect.isSelected
                if (newTitle.isEmpty()) {
                    Toast.makeText(this, "The title can not be blank!", Toast.LENGTH_SHORT).show()
                    return
                }
                commit(newTitle, newContent, newCollect)
            }

            binding.editCollect -> {
                if (!binding.editCollect.isSelected) {
                    binding.editCollect.isSelected = !binding.editCollect.isSelected
                    Toast.makeText(
                        this@NoteEditActivity, "You have collected this notes.", Toast.LENGTH_SHORT
                    ).show()
                    CoroutineScope(Dispatchers.IO).launch {
                        if (note != null) {
                            note!!.collect = binding.editCollect.isSelected
                            DataBaseManager.notesDatabase.noteDao().updateNote(note!!)
                        }
//                        note?.apply {
//                            note!!.collect = binding.editCollect.isSelected
//                        }?.let { DataBaseManager.notesDatabase.noteDao().updateNote(it) }
                    }
                } else {
                    binding.editCollect.isSelected = !binding.editCollect.isSelected
                    Toast.makeText(
                        this@NoteEditActivity, "You canceled the collection of this notes.", Toast.LENGTH_SHORT
                    ).show()
                    CoroutineScope(Dispatchers.IO).launch {
                        note?.apply {
                            note!!.collect = binding.editCollect.isSelected
                        }?.let { DataBaseManager.notesDatabase.noteDao().updateNote(it) }
                    }
                }
            }
        }
    }

    private fun commit(newTitle: String, newContent: String, newCollect: Boolean) {
        if (notetype == 0) {
            DataBaseManager.insertNote(
                Note(
                    title = newTitle,
                    content = newContent,
                    color = selectedColor,
                    collect = newCollect
                )
            )
        } else {
            if (note != null) {
                note!!.title = newTitle
                note!!.content = newContent
                note!!.color = selectedColor
                DataBaseManager.updateNote(note!!)
            }
        }
        finish()
    }
}

