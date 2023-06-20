package com.example.todoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Random

class TodoAdapter(private val list: List<TodoModel>):RecyclerView.Adapter<TodoAdapter.ItemViewHolder>(){
    class ItemViewHolder(view: View):RecyclerView.ViewHolder(view){
        private val viewColorTag: View = view.findViewById(R.id.viewColorTag)
        private val txtShowTitle : TextView = view.findViewById(R.id.txtShowTitle)
        private val txtShowTask: TextView = view.findViewById(R.id.txtShowTask)
        private val txtShowCategory: TextView = view.findViewById(R.id.txtShowCategory)
        private val txtShowDate: TextView = view.findViewById(R.id.txtShowDate)
        private val txtShowTime: TextView = view.findViewById(R.id.txtShowTime)
        fun bind(todoModel: TodoModel) {
            with(itemView){
                val colors = resources.getIntArray(R.array.random_color)
                val randomColor = colors[Random().nextInt(colors.size)]
                viewColorTag.setBackgroundColor(randomColor)
                txtShowTitle.text = todoModel.title
                txtShowTask.text = todoModel.description
                txtShowCategory.text = todoModel.category
                updateTime(todoModel.time)
                updateDate(todoModel.date)
            }
        }
        private fun updateTime(time: Long) {
            val myFormat = "h:mm a"
            val sdf = SimpleDateFormat(myFormat, Locale("en","In"))
            txtShowTime.text = sdf.format(Date(time))
        }
        private fun updateDate(date: Long) {
            val myFormat = "EEE, d MM yyyy"
            val sdf = SimpleDateFormat(myFormat, Locale("en","In"))
            txtShowDate.text = sdf.format(Date(date))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.item_todo,parent,false)
        return ItemViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(list[position])
    }
}