package com.sunling.softapp.timbernoteash.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sunling.softapp.timbernoteash.R
import com.sunling.softapp.timbernoteash.databinding.ItemColorBinding
import com.sunling.softapp.timbernoteash.ui.listener.ClickActionListener

class ColorsAdapter(val context: Context, val listener: ClickActionListener) :
    RecyclerView.Adapter<ColorsAdapter.SelectColorsVH>() {

    private lateinit var colorsData: IntArray
    private var selected_id = 0

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: IntArray) {
        colorsData = data
        notifyDataSetChanged()
    }

    inner class SelectColorsVH(private val binding: ItemColorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val adapterPosition = adapterPosition
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    selected_id = adapterPosition
                    listener.colorsSelect(adapterPosition)
                    notifyDataSetChanged()
                }
            }
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        fun setBinding(context: Context, colorDraw: Int, position: Int) {
            if (selected_id == position) {
                binding.itemColorsLayout.background = context.getDrawable(R.drawable.shape_colors_border)
            } else {
                binding.itemColorsLayout.background = null
            }
            binding.itemColor.setImageResource(colorDraw)
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectColorsVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemColorBinding.inflate(inflater, parent, false)
        return SelectColorsVH(binding)
    }

    override fun getItemCount(): Int {
        return colorsData.size
    }

    override fun onBindViewHolder(holder: SelectColorsVH, position: Int) {
        val color = colorsData[position]
        holder.setBinding(context, color, position)
    }

}