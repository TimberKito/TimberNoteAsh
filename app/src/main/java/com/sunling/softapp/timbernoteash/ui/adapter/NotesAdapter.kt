package com.sunling.softapp.timbernoteash.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sunling.softapp.timbernoteash.R
import com.sunling.softapp.timbernoteash.databinding.ItemNoteBinding
import com.sunling.softapp.timbernoteash.entity.Note
import com.sunling.softapp.timbernoteash.ui.listener.ClickActionListener

class NotesAdapter(val context: Context, val listener: ClickActionListener) :
    RecyclerView.Adapter<NotesAdapter.NotesVH>() {

    private val TYPE_HEAD = 0
    private val TYPE_CONTENT = 1
    private var notesList: MutableList<Note> = mutableListOf()
    private val notesBgColor = intArrayOf(
        R.drawable.shape_notes_00,
        R.drawable.shape_notes_01,
        R.drawable.shape_notes_02,
        R.drawable.shape_notes_03,
        R.drawable.shape_notes_04,
        R.drawable.shape_notes_05,
        R.drawable.shape_notes_06
    )

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(data: MutableList<Note>) {
        notesList = data
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            TYPE_HEAD
        } else {
            TYPE_CONTENT
        }
    }

    inner class NotesVH(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = getAdapterPosition()
                if (position != RecyclerView.NO_POSITION) {
                    if (position > 0) {
                        val item = notesList[position - 1]
                        listener.clickAction(position, item)
                    } else {
                        listener.clickAction(position, null)
                    }

                }
            }
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(context: Context, note: Note?, position: Int) {
            val itemViewType = getItemViewType(position)
            binding.apply {
                if (itemViewType == TYPE_HEAD) {
                    itemLayout.background = context.getDrawable(R.drawable.shape_notes_02)
                    itemTitle.setTextColor(context.getColor(R.color.yellow))
                    itemTitle.text = context.getString(R.string.blank_notes)
                    itemAdd.visibility = View.VISIBLE
                } else {
                    note?.let {
                        itemLayout.background = context.getDrawable(notesBgColor[it.color])
                        itemTitle.text = it.title
                        itemContent.text = it.content
                    }
                    itemTitle.setTextColor(context.getColor(R.color.black))
                    itemContent.setTextColor(context.getColor(R.color.black))
                    itemAdd.visibility = View.GONE
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNoteBinding.inflate(inflater, parent, false)
        return NotesVH(binding)
    }

    override fun getItemCount(): Int {
        return notesList.size + 1
    }

    override fun onBindViewHolder(holder: NotesVH, position: Int) {
        if (position > 0) {
            val note = notesList[position - 1]
            holder.bind(context, note, position)
        } else {
            holder.bind(context, null, position)
        }
    }

}