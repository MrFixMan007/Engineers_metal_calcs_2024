package com.example.engineersmetalcalcs.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.engineersmetalcalcs.R
import com.example.engineersmetalcalcs.databinding.ChapterListItemBinding
import com.example.engineersmetalcalcs.renderDto.Chapter

class ChapterAdapter: RecyclerView.Adapter<ChapterAdapter.ChapterHolder>() {
    val chapterList = ArrayList<Chapter>()
    class ChapterHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = ChapterListItemBinding.bind(item)
        fun bind(chapter: Chapter) = with(binding){
            ChapterButton.text = chapter.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chapter_list_item, parent, false)
        return ChapterHolder(view)
    }

    override fun onBindViewHolder(holder: ChapterHolder, position: Int) {
        holder.bind(chapterList[position])
    }

    override fun getItemCount(): Int {
        return chapterList.size
    }

    fun addChapter(chapter: Chapter){
        chapterList.add(chapter)
        notifyDataSetChanged()
    }
}