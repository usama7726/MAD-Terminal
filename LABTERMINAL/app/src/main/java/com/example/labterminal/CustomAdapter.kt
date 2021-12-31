package com.example.labterminal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_2.view.*
import kotlinx.android.synthetic.main.layout_2.view.*

class CustomAdapter : RecyclerView.Adapter<viewholder>() {

    val title = arrayListOf<String>("Name : Usama , Registration : 079 , Phone:03065779468",
        "Name : Rizwan , Registration : 030 , Phone:03120389977",
        "Name : Huzaifa , Registration : 066 , Phone:03120376587",
        "Name : Wajid , Registration : 044 , Phone:03120378887"
    )
    override fun getItemCount(): Int {
        return title.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {


        val linflater = LayoutInflater.from(parent?.context)
        val newinf = linflater.inflate(R.layout.layout_2,parent,false)
        return viewholder(newinf)


    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val titlev = title.get(position)
        holder.v.textView.text=titlev
    }

}
class  viewholder(val v:View):RecyclerView.ViewHolder(v){

}