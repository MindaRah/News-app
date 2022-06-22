package com.example.mvvmretrofitapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmretrofitapp.R
import com.example.mvvmretrofitapp.model.Data
//NewsViewHolder
class NewsAdapter: RecyclerView.Adapter<NewsViewHolder>(){

    private var userList: MutableList<Data?> = mutableListOf()

    fun setUsersData(userList: MutableList<Data?>){
        this.userList.clear()
        this.userList=userList
        notifyDataSetChanged()
    }

    //var on


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.lay_news,parent,false).apply {
            return  NewsViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentUser = userList[position]
        holder.setInformationToTheViewHolder(currentUser)
    }

    override fun getItemCount(): Int = userList.size

}

class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    val rootView = itemView
    private val authorName: TextView = itemView.findViewById(R.id.author_view)
   // private val contentName : TextView = itemView.findViewById(R.id.content_view)
    private val date: TextView = itemView.findViewById(R.id.country_view)
    private val title: TextView = itemView.findViewById(R.id.title_view)
    private val image: ImageView = itemView.findViewById(R.id.user_image)
    private val time: TextView = itemView.findViewById(R.id.time_view)

    fun setInformationToTheViewHolder(currentData: Data?) {
        if (currentData != null) {
            authorName.text = "Author : " + currentData.author
        }
        // if (currentData != null) {
        //    contentName.text = "Content :  " + currentData.content
        //}
        if (currentData != null) {
            date.text = "Date " + currentData.date
        }
        if (currentData != null) {
            title.text = currentData.title
        }
        if (currentData != null) {
            Glide.with(itemView)
                .load(currentData.imageUrl)
                .centerCrop()
                .into(image)
        }
        if (currentData != null) {
            time.text = "Time " + currentData.time
        }
    }
}