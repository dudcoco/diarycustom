package com.example.diarycustom

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*


class DiarywriteFragment : Fragment() {
    var mMode = AppConstants.MODE_INSERT
    var item: Note? = null
    var listener: OnTabItemSelectedListener? = null
    var dateTextView: TextView? = null
    var locationTextView: TextView? = null
    var contentsInput: EditText? = null
    var pictureImageView: ImageView? = null
    var resultPhotoBitmap: Bitmap? = null
    var todayDateFormat: SimpleDateFormat? = null
    var currentDateString: String? = null

    lateinit var rootView: View

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is OnTabItemSelectedListener) {
            listener = context
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
        rootView = inflater.inflate(R.layout.fragment_diarywrite, container, false) as ViewGroup

        initUI()
        applyItem()
        return rootView
    }

    private fun initUI() {
        dateTextView = rootView.findViewById(R.id.dateTextView)
        locationTextView = rootView.findViewById(R.id.locationTextView)
        contentsInput = rootView.findViewById(R.id.contentsInput)
        pictureImageView = rootView.findViewById(R.id.pictureImageView)
    }
    fun applyItem() {
        if (item != null) {
            mMode = AppConstants.MODE_MODIFY
            assignAddress(item!!.address)
            assignDateString(item!!.createDateStr)
            assignContents(item!!.contents)
            val picturePath: String = item!!.picture
            if (picturePath == null || picturePath == "") {
                pictureImageView!!.setImageResource(R.drawable.noimagefound)
            } else {
                assignPicture(item!!.picture, 1)
            }
        } else {
            mMode = AppConstants.MODE_INSERT
            assignAddress("")
            val currentDate = Date()
            if (todayDateFormat == null) {
                todayDateFormat = SimpleDateFormat(resources.getString(R.string.today_date_format))
            }
            currentDateString = todayDateFormat!!.format(currentDate)
            assignDateString(currentDateString)
            contentsInput!!.setText("")
            pictureImageView!!.setImageResource(R.drawable.noimagefound)
        }
    }

    fun assignAddress(data: String?) {
        locationTextView!!.text = data
    }

    fun assignDateString(dateString: String?) {
        dateTextView!!.text = dateString
    }

    fun assignContents(data: String?) {
        contentsInput!!.setText(data)
    }

    fun assignPicture(picturePath: String?, sampleSize: Int) {
        val options = BitmapFactory.Options()
        options.inSampleSize = sampleSize
        resultPhotoBitmap = BitmapFactory.decodeFile(picturePath, options)
        pictureImageView!!.setImageBitmap(resultPhotoBitmap)
    }

    fun assignItem(item: Note?) {
        this.item = item
    }


}