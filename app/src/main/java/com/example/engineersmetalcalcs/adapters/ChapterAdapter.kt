package com.example.engineersmetalcalcs.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.engineersmetalcalcs.R
import com.example.engineersmetalcalcs.databinding.ChapterListItemBinding
import com.example.engineersmetalcalcs.listItem.Chapter

class ChapterAdapter(private val listener: Listener): RecyclerView.Adapter<ChapterAdapter.ChapterHolder>() {
    private val chapterList = ArrayList<Chapter>()
    class ChapterHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = ChapterListItemBinding.bind(item)
        fun bind(chapter: Chapter, listener: Listener) = with(binding){
            ChapterButton.text = chapter.title
            ChapterButton.setOnClickListener {
                listener.onClick(chapter)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chapter_list_item, parent, false)
        return ChapterHolder(view)
    }

    override fun onBindViewHolder(holder: ChapterHolder, position: Int) {
        holder.bind(chapterList[position], listener)
    }

    override fun getItemCount(): Int {
        return chapterList.size
    }

    fun addChapter(chapter: Chapter){
        chapterList.add(chapter)
        notifyDataSetChanged()
    }

    fun addAll(items: List<Chapter>){
        chapterList.addAll(items)
        notifyDataSetChanged()
    }

    interface Listener{
        fun onClick(item: Chapter)
    }
}