package com.example.diarycustom

interface OnTabItemSelectedListener {
    fun onTabSelected(position: Int)
    fun showDiaryWriteFragment(item: Note?)
}