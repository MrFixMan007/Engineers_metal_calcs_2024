package presentation.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import metalcalcs.feature_calc_temp_likvidus.R
import metalcalcs.feature_calc_temp_likvidus.databinding.PercentMetalListItemBinding
import presentation.model.PercentMetalModel

class PercentMetalAdapter(private val listener: Listener): RecyclerView.Adapter<PercentMetalAdapter.PercentMetalHolder>() {
    private val percentMetalList = ArrayList<PercentMetalModel>()
    class PercentMetalHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = PercentMetalListItemBinding.bind(item)
        fun bind(percentMetalModel: PercentMetalModel, listener: Listener) = with(binding){
            name.text = percentMetalModel.name

            if (percentMetalModel.value == 0f) value.setText("0.")
            else value.setText(percentMetalModel.value.toString())

            value.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if(s.toString() != "" && s.toString() != ".") percentMetalModel.value = s.toString().toFloat()
                    else percentMetalModel.value = 0f
                }

                override fun afterTextChanged(s: Editable?) {
                    listener.onValueChanged(percentMetalModel)
                }
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PercentMetalHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.percent_metal_list_item, parent, false)
        return PercentMetalHolder(view)
    }

    override fun onBindViewHolder(holder: PercentMetalHolder, position: Int) {
        holder.bind(percentMetalList[position], listener)
    }

    override fun getItemCount(): Int {
        return percentMetalList.size
    }

    fun addAll(items: List<PercentMetalModel>){
        if (percentMetalList.addAll(items))
            notifyItemRangeInserted(0, items.size)
    }

    interface Listener{
        fun onValueChanged(item: PercentMetalModel)
    }
}