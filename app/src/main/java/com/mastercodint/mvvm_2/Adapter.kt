package com.mastercodint.mvvm_2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class Adapter(val context:Context,val listner:INoteRvAdapter): RecyclerView.Adapter<Adapter.viewHolder>() {

    val allNotes=ArrayList<Note>()
    inner class viewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val text=itemView.findViewById<TextView>(R.id.textViewNote)
        val delete=itemView.findViewById<ImageView>(R.id.imageViewDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val viewHolder=viewHolder(LayoutInflater.from(context).inflate(R.layout.note_design,parent,false))
        viewHolder.delete.setOnClickListener {
            listner.OnItemClicked(allNotes[viewHolder.adapterPosition])

        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val currentNote=allNotes[position]
        holder.text.text=currentNote.text.toString()
    }
    fun updateList( newList:List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
    interface INoteRvAdapter{
        fun OnItemClicked(note:Note)
    }
}