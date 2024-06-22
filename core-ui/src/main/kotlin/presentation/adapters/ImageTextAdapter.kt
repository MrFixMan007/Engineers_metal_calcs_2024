package presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import metalcalcs.core_ui.R
import metalcalcs.core_ui.databinding.InfoImageItemBinding
import metalcalcs.core_ui.databinding.InfoImageTextItemBinding
import metalcalcs.core_ui.databinding.InfoTextItemBinding

class ImageTextAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val imageTextList = mutableListOf<ImageText>()

    companion object{
        const val TEXT = 1
        const val IMAGE = 2
        const val TEXT_IMAGE = 3
    }

    class ImageTextViewHolder(val context: Context, item: View): RecyclerView.ViewHolder(item) {
        private val binding = InfoImageTextItemBinding.bind(item)

        fun bind(imageText: ImageText) = with(binding){
            imageView.setImageResource(imageText.imageId!!)
            textView.text = imageText.text
        }
    }

    class TextViewHolder(val context: Context, item: View): RecyclerView.ViewHolder(item) {
        private val binding = InfoTextItemBinding.bind(item)

        fun bind(imageText: ImageText) = with(binding){
            textView.text = imageText.text
        }
    }

    class ImageViewHolder(val context: Context, item: View): RecyclerView.ViewHolder(item) {
        private val binding = InfoImageItemBinding.bind(item)

        fun bind(imageText: ImageText) = with(binding){
            imageView.setImageResource(imageText.imageId!!)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TEXT_IMAGE -> {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.info_image_text_item, parent, false
                )
                ImageTextViewHolder(context = parent.context, item = view)
            }
            IMAGE -> {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.info_image_item, parent, false
                )
                ImageViewHolder(context = parent.context, item = view)
            }
            TEXT -> {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.info_text_item, parent, false
                )
                TextViewHolder(context = parent.context, item = view)
            }
            else -> throw IllegalArgumentException("Invalid view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ImageTextViewHolder -> {
                holder.bind(imageText = imageTextList[position])
            }
            is TextViewHolder -> {
                holder.bind(imageText = imageTextList[position])
            }
            is ImageViewHolder -> {
                holder.bind(imageText = imageTextList[position])
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (imageTextList[position].text != null && imageTextList[position].imageId != null)
            TEXT_IMAGE
        else if (imageTextList[position].text != null && imageTextList[position].imageId == null)
            TEXT
        else if (imageTextList[position].text == null && imageTextList[position].imageId != null)
            IMAGE
        else 0
    }

    override fun getItemCount() = imageTextList.size

    fun addAll(items: List<ImageText>){
        if (imageTextList.addAll(items))
            notifyItemRangeInserted(0, items.size)
    }

}

data class ImageText(
    val text: String? = null,
    val imageId: Int? = null,
    val textSize: Float? = null,
)