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

class CollectAdapter(private val context: Context, val listener: ClickActionListener) :
    RecyclerView.Adapter<CollectAdapter.ItemVH>() {

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

    inner class ItemVH(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = getAdapterPosition()
                val item = notesList[position]
                listener.clickAction(position, item)
            }
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(context: Context, note: Note?, position: Int) {
            binding.apply {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNoteBinding.inflate(inflater, parent, false)
        return ItemVH(binding)
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: ItemVH, position: Int) {
        val note = notesList[position]
        holder.bind(context, note, position)
    }

}