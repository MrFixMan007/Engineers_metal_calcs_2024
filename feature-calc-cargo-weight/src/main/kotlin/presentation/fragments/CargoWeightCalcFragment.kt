package presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import metalcalcs.core_ui.R
import metalcalcs.feature_calc_cargo_weight.databinding.FragmentCargoWeightCalcBinding
import presentation.adapters.LongCalcAdapter
import presentation.model.CalcUnit


class CargoWeightCalcFragment(private val adapter1: LongCalcAdapter,
                              private val adapter2: LongCalcAdapter,
                              private val calcUnits1: List<CalcUnit>,
                              private val calcUnits2: List<CalcUnit>) : Fragment() {

    private lateinit var binding: FragmentCargoWeightCalcBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCargoWeightCalcBinding.inflate(layoutInflater)

        CoroutineScope(Dispatchers.IO).launch {
            init()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private suspend fun init() = withContext(Dispatchers.Main){
        binding.apply {
            typeTextView1.text = binding.root.context.getString(R.string.type1)
            typeTextView2.text = binding.root.context.getString(R.string.type2)

            rcViewCargoWeight.layoutManager = LinearLayoutManager(context)
            rcViewCargoWeight.adapter = adapter1

            adapter1.addAll(calcUnits1)

            rcViewCargoWeight1.layoutManager = LinearLayoutManager(context)
            rcViewCargoWeight1.adapter = adapter2

            adapter2.addAll(calcUnits2)
        }
    }
}