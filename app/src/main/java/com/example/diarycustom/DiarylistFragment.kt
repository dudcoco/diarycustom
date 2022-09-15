package com.example.diarycustom

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class DiarylistFragment : Fragment() {
    lateinit var adapter: NoteAdapter
    lateinit var recyclerView: RecyclerView
    var listener: OnTabItemSelectedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is OnTabItemSelectedListener) {
            listener = context
        }
        else {
            Log.d("TEST", "Not OnTabItemSelectedListener.")
        }
    }

    override fun onDetach() {
        super.onDetach()
        if (context != null) {
            listener = null
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView: View = inflater.inflate(R.layout.fragment_diarylist, container, false)
        initUI(rootView)
        return rootView
    }

    fun initUI(rootView: View) {

        val todayWriteButton = rootView.findViewById<Button>(R.id.WriteButton)
        todayWriteButton.setOnClickListener {
            listener?.onTabSelected(1)
        }

        adapter.setOnItemClickListener(object : OnNoteItemClickListener {
            override fun onItemClick(holder: NoteAdapter.ViewHolder, view: View, position: Int) {
                val item: Note? = adapter.getItem(position)
                if (item != null) {
                    Log.d("DiaryListFragment", "아이템 선택됨 : " + item._id)
                }
                listener?.showDiaryWriteFragment(item)
            }
        })

        recyclerView = rootView.findViewById(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager

        adapter = NoteAdapter()

        recyclerView.adapter = adapter


        //리사이클러뷰에 데이터 제공
        adapter.addItem(
            Note(0, "북구 용봉동", "", "", "존나 힘들다", "", "9월 14일")
        )

//        adapter.addItem(
//            Note(1, "북구 용봉동", "", "", "존나 힘들다", "9월 14일")
//        )
    }
}