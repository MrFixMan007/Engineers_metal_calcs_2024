package presentation.adapters

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import metalcalcs.core_ui.R
import metalcalcs.core_ui.databinding.LongInputWithDescriptionItemBinding
import metalcalcs.core_ui.databinding.LongInputWithDescriptionWithSpinnerItemBinding
import metalcalcs.core_ui.databinding.LongInputWithDescriptionWithStrongMeasuringItemBinding
import metalcalcs.core_ui.databinding.LongOutputWithDescriptionWithSpinnerItemBinding
import presentation.adapters.event.AdapterEvent
import presentation.adapters.event.TypeEvent
import presentation.mapper.StringResourceMapper
import presentation.model.CalcUnit

class LongCalcAdapter(private val listener: Listener,
                      private val stringResourceMapper: StringResourceMapper,
                      private val recyclerViewId: Int = 0): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val calcList = ArrayList<CalcUnit>()
    private lateinit var outputHolder: LongOutputCalcHolder
    class LongInputCalcHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = LongInputWithDescriptionWithSpinnerItemBinding.bind(item)
        fun bind(calcUnit: CalcUnit, listener: Listener,
                 stringResourceMapper: StringResourceMapper,
                 recyclerViewId: Int = 0) = with(binding){
            descriptionTextView.text = calcUnit.description

            if(calcUnit.value != 0f)
                inputEditText.setText(calcUnit.value.toString())
            else inputEditText.setText("")
            inputEditText.setOnKeyListener { v, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                    val inputMethodManager = root.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(v.windowToken, 0)
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
            inputEditText.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if(s.toString() != "" && s.toString() != ".") calcUnit.value = s.toString().toFloat()
                    else calcUnit.value = 0f
                }

                override fun afterTextChanged(s: Editable?) {
                    listener.onClick(AdapterEvent(recyclerViewId, TypeEvent.TextChanged))
                }
            })

            val arr = binding.root.context.resources.getStringArray(stringResourceMapper.getStringArrayResourceId(calcUnit.measuredIn))
            val adapter = ArrayAdapter.createFromResource(itemView.context,
                stringResourceMapper.getStringArrayResourceId(calcUnit.measuredIn), android.R.layout.simple_spinner_item)

            adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item)
            spinnerUnitOfMeasurement.adapter = adapter
            spinnerUnitOfMeasurement.setSelection(arr.indexOf(calcUnit.measuredIn))
            spinnerUnitOfMeasurement.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    calcUnit.measuredIn = parent?.getItemAtPosition(position).toString()
                    listener.onClick(AdapterEvent(recyclerViewId, TypeEvent.MeasureChanged))
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }
    }

    class LongInputCoefCalcHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = LongInputWithDescriptionItemBinding.bind(item)
        fun bind(calcUnit: CalcUnit, listener: Listener, recyclerViewId: Int = 0) = with(binding){
            descriptionTextView.text = calcUnit.description

            if(calcUnit.value != 0f)
                inputEditText.setText(calcUnit.value.toString())
            else inputEditText.setText("")
            inputEditText.setOnKeyListener { v, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                    val inputMethodManager = root.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(v.windowToken, 0)
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
            inputEditText.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if(s.toString() != "" && s.toString() != ".") calcUnit.value = s.toString().toFloat()
                    else calcUnit.value = 0f
                }

                override fun afterTextChanged(s: Editable?) {
                    listener.onClick(AdapterEvent(recyclerViewId, TypeEvent.TextChanged))
                }
            })
        }
    }

    class LongInputCalcHolderWithStrongMeasure(item: View): RecyclerView.ViewHolder(item) {
        private val binding = LongInputWithDescriptionWithStrongMeasuringItemBinding.bind(item)
        fun bind(calcUnit: CalcUnit, listener: Listener, recyclerViewId: Int = 0) = with(binding){
            descriptionTextView.text = calcUnit.description

            if(calcUnit.value != 0f)
                inputEditText.setText(calcUnit.value.toString())
            else inputEditText.setText("")
            unitOfMeasurementTextView.text = calcUnit.measuredIn
            inputEditText.setOnKeyListener { v, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                    val inputMethodManager = root.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(v.windowToken, 0)
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
            inputEditText.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if(s.toString() != "" && s.toString() != ".") {
                        calcUnit.value = s.toString().toFloat()
                    }
                    else {
                        calcUnit.value = 0f
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    listener.onClick(AdapterEvent(recyclerViewId, TypeEvent.TextChanged))
                }
            })
        }
    }

    class LongOutputCalcHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = LongOutputWithDescriptionWithSpinnerItemBinding.bind(item)
        private var isProgramChanged = true
        fun bind(calcUnit: CalcUnit, listener: Listener,
                 stringResourceMapper: StringResourceMapper,
                 recyclerViewId: Int = 0) = with(binding){
            descriptionTextView.text = calcUnit.description
            inputEditText.setText(calcUnit.value.toString())
            inputEditText.keyListener = null

            val arr = binding.root.context.resources.getStringArray(stringResourceMapper.getStringArrayResourceId(calcUnit.measuredIn))
            val adapter = ArrayAdapter.createFromResource(itemView.context,
                stringResourceMapper.getStringArrayResourceId(calcUnit.measuredIn),
                android.R.layout.simple_spinner_item)

            adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item)
            spinnerUnitOfMeasurement.adapter = adapter
            spinnerUnitOfMeasurement.setSelection(arr.indexOf(calcUnit.measuredIn))
            spinnerUnitOfMeasurement.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    calcUnit.measuredIn = parent?.getItemAtPosition(position).toString()
                    isProgramChanged = if(!isProgramChanged) {
                        listener.onClick(AdapterEvent(recyclerViewId, TypeEvent.MeasureChanged))
                        false
                    } else false
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }
        fun updateOutputEditText(){
            isProgramChanged = true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            CalcUnit.INPUT -> {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.long_input_with_description_with_spinner_item, parent, false)
                LongInputCalcHolder(view)
            }
            CalcUnit.INPUT_COEFICIENT -> {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.long_input_with_description_item, parent, false)
                LongInputCoefCalcHolder(view)
            }
            CalcUnit.INPUT_STRONG_MEASURE -> {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.long_input_with_description_with_strong_measuring_item, parent, false)
                LongInputCalcHolderWithStrongMeasure(view)
            }
            CalcUnit.OUTPUT -> {
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
                holder.bind(
                    calcUnit = calcList[position],
                    listener = listener,
                    stringResourceMapper = stringResourceMapper,
                    recyclerViewId = recyclerViewId)
            }
            is LongInputCoefCalcHolder -> {
                holder.bind(
                    calcUnit = calcList[position],
                    listener = listener,
                    recyclerViewId = recyclerViewId)
            }
            is LongInputCalcHolderWithStrongMeasure -> {
                holder.bind(
                    calcUnit = calcList[position],
                    listener = listener,
                    recyclerViewId = recyclerViewId)
            }
            is LongOutputCalcHolder -> {
                outputHolder = holder
                holder.bind(
                    calcUnit = calcList[position],
                    listener = listener,
                    stringResourceMapper = stringResourceMapper,
                    recyclerViewId = recyclerViewId)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return calcList[position].type
    }

    fun addAll(items: List<CalcUnit>){
        if (calcList.addAll(items))
            notifyItemRangeInserted(0, items.size)
    }

    fun updateOutputValue(){
        val outCalcUnit = calcList.find { it.type == CalcUnit.OUTPUT }
        outputHolder.updateOutputEditText()
        notifyItemChanged(calcList.indexOf(outCalcUnit))
    }

    interface Listener{
        fun onClick(recyclerViewId: AdapterEvent)
    }
}