package com.sunling.softapp.timbernoteash.ui.listener

import com.sunling.softapp.timbernoteash.entity.Note

interface ClickActionListener {
    fun clickAction(i: Int, note: Note?)
    fun colorsSelect(i: Int)
}