package com.example.eventapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val eventoList : ArrayList<Evento>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListener
   // private val titles = arrayOf(R.string.day1title, R.string.day2title, R.string.day3title, R.string.day4title, R.string.day5title)
   // private val details = arrayOf(R.string.day1desc, R.string.day2desc, R.string.day3desc, R.string.day4desc, R.string.day5desc)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(v,mListener)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val currentItem = eventoList[position]
        holder.itemTitle.text = currentItem.title
        holder.itemDetail.text = currentItem.timeStart + " - " + currentItem.timeEnd
    }

    override fun getItemCount(): Int {
        return eventoList.size
    }

    interface onItemClickListener{

        fun onItemClick(position: Int)

    }

    fun setOnItemClickListener(listener: onItemClickListener){

        mListener = listener

    }

    inner class ViewHolder(itemView: View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView){

        var itemTitle: TextView
        var itemDetail: TextView


        init {
            itemTitle = itemView.findViewById(R.id.item_title)
            itemDetail = itemView.findViewById(R.id.item_detail)

            //val position: Int = bindingAdapterPosition

            itemView.setOnClickListener {
                listener.onItemClick(bindingAdapterPosition)
                /*

                val position: Int = bindingAdapterPosition
                Toast.makeText(itemView.context, "You clicked on ${eventoList[position].title}", Toast.LENGTH_LONG).show()

                 */
            }

        }

    }


}