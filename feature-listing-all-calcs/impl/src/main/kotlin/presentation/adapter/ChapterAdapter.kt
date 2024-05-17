package presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import metalcalcs.feature_listing_all_calcs.impl.R
import metalcalcs.feature_listing_all_calcs.impl.databinding.ChapterListItemBinding
import presentation.model.Chapter

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

    fun addAll(items: List<Chapter>){
        if (chapterList.addAll(items))
            notifyItemRangeInserted(0, items.size)
    }

    interface Listener{
        fun onClick(item: Chapter)
    }
}