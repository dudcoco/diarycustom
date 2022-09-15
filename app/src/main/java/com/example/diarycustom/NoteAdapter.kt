package com.example.diarycustom

import android.net.Uri
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.ViewHolder>(), OnNoteItemClickListener { //myviewholder에서 my빼기
    var items = ArrayList<Note>()
    var listener: OnNoteItemClickListener? = null
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //        var pictureExistsImageView: ImageView
        var contentsTextView: TextView
        var pictureImageView: ImageView
        var locationTextView: TextView
        var dateTextView: TextView


        init {
            pictureImageView = itemView.findViewById(R.id.pictureImageView)
            contentsTextView = itemView.findViewById(R.id.contentsTextView)
//
            locationTextView = itemView.findViewById(R.id.locationTextView)
            dateTextView = itemView.findViewById(R.id.dateTextView)

            itemView.setOnClickListener {
                val position = adapterPosition
                listener?.onItemClick(this, itemView, position)
            }
        }


        public fun setItem(item: Note) {
//            val mood: String = item.mood
//            setMoodImage(mood)

            val picturePath = item.picture
            if (picturePath != null && picturePath != "") {
//                pictureExistsImageView.visibility = View.VISIBLE
                pictureImageView.visibility = View.VISIBLE
                pictureImageView.setImageURI(Uri.parse("file://" + picturePath))
            } else {
                pictureImageView.visibility = View.GONE
                pictureImageView.setImageResource(R.drawable.ic_fortune)
            }

        }
    }

    fun setOnItemClickListener(listener: OnNoteItemClickListener?) {
        this.listener = listener
    }

    fun getItem(position: Int): Note? {
        return items[position]
    }

    override fun onItemClick(holder: ViewHolder, view: View, position: Int) {
        if (listener != null) {
            listener?.onItemClick(holder, view, position)
        } else {
            Log.d("TEST", "onItemClick null")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return ViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.setItem(item)
    }

    override fun getItemCount() = items.size

    fun addItem(item: Note) {
        items.add(item)
    }
}


