package com.example.engineersmetalcalcs.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.engineersmetalcalcs.R
import com.example.engineersmetalcalcs.databinding.LongInputWithDescriptionBinding
import com.example.engineersmetalcalcs.databinding.LongInputWithDescriptionWithSpinnerItemBinding
import com.example.engineersmetalcalcs.databinding.LongOutputWithDescriptionWithSpinnerItemBinding
import com.example.engineersmetalcalcs.listItem.LongCalc
import com.example.engineersmetalcalcs.unitsOfMeasurement.StringArrayMapper

class LongCalcAdapter(private val listener: Listener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val calcList = ArrayList<LongCalc>()
    class LongInputCalcHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = LongInputWithDescriptionWithSpinnerItemBinding.bind(item)
        fun bind(longCalc: LongCalc, listener: Listener) = with(binding){
            descriptionTextView.text = longCalc.description
            inputEditText.setText(longCalc.value.toString())

            val mapper = StringArrayMapper(itemView.context)
            val adapter = ArrayAdapter.createFromResource(itemView.context,
                mapper.getStringArrayResourceId(longCalc.measuredIn), android.R.layout.simple_spinner_item)

            adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item)
            spinnerUnitOfMeasurement.adapter = adapter
            spinnerUnitOfMeasurement.setSelection(mapper.getPosition(longCalc.measuredIn))
            spinnerUnitOfMeasurement.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    listener.onClick(longCalc)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }
    }

    class LongInputCoefCalcHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = LongInputWithDescriptionBinding.bind(item)
        fun bind(longCalc: LongCalc, listener: Listener) = with(binding){
            descriptionTextView.text = longCalc.description
            inputEditText.setText(longCalc.value.toString())
        }
    }

    class LongOutputCalcHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = LongOutputWithDescriptionWithSpinnerItemBinding.bind(item)
        fun bind(longCalc: LongCalc, listener: Listener) = with(binding){
            descriptionTextView.text = longCalc.description
            inputEditText.setText(longCalc.value.toString())
            inputEditText.keyListener = null

            val mapper = StringArrayMapper(itemView.context)
            val adapter = ArrayAdapter.createFromResource(itemView.context,
                mapper.getStringArrayResourceId(longCalc.measuredIn),
                android.R.layout.simple_spinner_item)

            adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item)
            spinnerUnitOfMeasurement.adapter = adapter
            spinnerUnitOfMeasurement.setSelection(mapper.getPosition(longCalc.measuredIn))
            spinnerUnitOfMeasurement.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    listener.onClick(longCalc)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            LongCalc.INPUT -> {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.long_input_with_description_with_spinner_item, parent, false)
                LongInputCalcHolder(view)
            }
            LongCalc.INPUT_COEFICIENT -> {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.long_input_with_description, parent, false)
                LongInputCoefCalcHolder(view)
            }
            LongCalc.OUTPUT -> {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.long_output_with_description_with_spinner_item, parent, false)
                LongOutputCalcHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
        return calcList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is LongInputCalcHolder -> {
                holder.bind(calcList[position], listener)
            }
            is LongInputCoefCalcHolder -> {
                holder.bind(calcList[position], listener)
            }
            is LongOutputCalcHolder -> {
                holder.bind(calcList[position], listener)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return calcList[position].type
    }

    fun addAll(items: List<LongCalc>){
        calcList.addAll(items)
        notifyDataSetChanged()
    }

    interface Listener{
        fun onClick(item: LongCalc)
    }
}