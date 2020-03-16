package com.zaatsiyah.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_note.view.*
import java.text.FieldPosition

class NoteAdapter (var list: List<NoteModel>)
    : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View)
        :RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            itemView.in_tv_date.text = list[position].createdAt
            itemView.in_tv_title.text = list[position].title
            itemView.in_tv_message.text = list[position].message
        }
    }

    //nyambungin antara tampilan, membuat setting tampilan
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_note,parent, false)
        )
    }

    //mengatur jumlah data yang ingin ditampilkan
    override fun getItemCount(): Int {
        return list.size
    }

    //data dari list dan tampilan digabungin
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    //untuk update
    fun setListNote(list: List<NoteModel>) {
        this.list = list
        notifyDataSetChanged()
    }

}