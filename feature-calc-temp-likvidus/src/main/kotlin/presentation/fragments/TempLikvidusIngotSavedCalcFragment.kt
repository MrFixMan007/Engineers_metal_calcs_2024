package presentation.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import data.model.calcNamesEnum.TempLikvidusNameEnum
import domain.usecase.temperatureIngot.inputParam.TemperatureIngotInputParam
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import metalcalcs.core_ui.R
import metalcalcs.feature_calc_temp_likvidus.databinding.FragmentTempLikvidusIngotSavedCalcBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import presentation.adapter.PercentMetalAdapter
import presentation.model.PercentMetalModel
import presentation.viewmodel.CalcIngotEvent
import presentation.viewmodel.SaveCalcTempLikvidIngotEvent
import presentation.viewmodel.TempLikvidusIngotSavedViewModel

class TempLikvidusIngotSavedCalcFragment : Fragment(), PercentMetalAdapter.Listener, MenuProvider {

    private lateinit var binding: FragmentTempLikvidusIngotSavedCalcBinding

    private val vm : TempLikvidusIngotSavedViewModel by viewModel()
    private lateinit var percentMetalMap: MutableMap<TempLikvidusNameEnum, PercentMetalModel>

    private lateinit var adapter : PercentMetalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentTempLikvidusIngotSavedCalcBinding.inflate(layoutInflater)

        init()
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
        requireActivity().addMenuProvider(this, viewLifecycleOwner)
    }

    override fun onValueChanged(item: PercentMetalModel) {
        if (percentMetalMap.all { it.value.value == 0f }){
            binding.apply {
                res.text = ""
                resLower.text = ""
                resUpper.text = ""
                resLowerInFurnace.text = ""
                resUpperInFurnace.text = ""
            }
        }
        else
        {
            send()
        }
    }

    private fun send(){
        CoroutineScope(Dispatchers.Main).launch {
            vm.send(
                CalcIngotEvent(
                    TemperatureIngotInputParam(
                        w = percentMetalMap[TempLikvidusNameEnum.W]!!.value,
                        cr = percentMetalMap[TempLikvidusNameEnum.Cr]!!.value,
                        co = percentMetalMap[TempLikvidusNameEnum.Co]!!.value,
                        mo = percentMetalMap[TempLikvidusNameEnum.Mo]!!.value,
                        v = percentMetalMap[TempLikvidusNameEnum.V]!!.value,
                        al = percentMetalMap[TempLikvidusNameEnum.Al]!!.value,
                        ni = percentMetalMap[TempLikvidusNameEnum.Ni]!!.value,
                        mn = percentMetalMap[TempLikvidusNameEnum.Mn]!!.value,
                        cu = percentMetalMap[TempLikvidusNameEnum.Cu]!!.value,
                        si = percentMetalMap[TempLikvidusNameEnum.Si]!!.value,
                        ti = percentMetalMap[TempLikvidusNameEnum.Ti]!!.value,
                        s = percentMetalMap[TempLikvidusNameEnum.S]!!.value,
                        p = percentMetalMap[TempLikvidusNameEnum.P]!!.value,
                        c = percentMetalMap[TempLikvidusNameEnum.C]!!.value,
                    )
                )
            )
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.toolbar_calc_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when(menuItem.itemId){
            R.id.save -> save()
            android.R.id.home -> findNavController().popBackStack()
        }
        return true
    }

    private fun init(){
        percentMetalMap = vm.getSavedValuesMap()
        adapter = PercentMetalAdapter(this)
        val listPercentMetal = mutableListOf<PercentMetalModel>()
        percentMetalMap.forEach { (_, u) ->  listPercentMetal.add(u)}

        binding.apply {
            title.text = arguments?.getString("title")
            rcViewTempLikvidus.adapter = adapter
            rcViewTempLikvidus.layoutManager = GridLayoutManager(context, 3)
            adapter.addAll(listPercentMetal)

            vm.resultLive.observe(this@TempLikvidusIngotSavedCalcFragment) { state ->
                res.text = "${state.res}"
                resLower.text = "${state.resLower}"
                resUpper.text = "${state.resUpper}"
                resLowerInFurnace.text = "${state.resLowerInFurnace}"
                resUpperInFurnace.text = "${state.resUpperInFurnace}"
            }
            send()
        }
    }

    private fun save() {
        val builder = AlertDialog.Builder(context)

        val customLayout: View = layoutInflater.inflate(R.layout.custom_alert_dialog, null)
        builder.setView(customLayout)

        val nameInput = customLayout.findViewById<EditText>(R.id.nameOfSave)
        val descriptionInput = customLayout.findViewById<EditText>(R.id.descriptionOfSave)

        builder
            .setPositiveButton(R.string.ready)
            { _, _ ->
                CoroutineScope(Dispatchers.Main).launch {
                    val saveCalcEvent = SaveCalcTempLikvidIngotEvent(
                        param = TemperatureIngotInputParam(
                            w = percentMetalMap[TempLikvidusNameEnum.W]!!.value,
                            cr = percentMetalMap[TempLikvidusNameEnum.Cr]!!.value,
                            co = percentMetalMap[TempLikvidusNameEnum.Co]!!.value,
                            mo = percentMetalMap[TempLikvidusNameEnum.Mo]!!.value,
                            v = percentMetalMap[TempLikvidusNameEnum.V]!!.value,
                            al = percentMetalMap[TempLikvidusNameEnum.Al]!!.value,
                            ni = percentMetalMap[TempLikvidusNameEnum.Ni]!!.value,
                            mn = percentMetalMap[TempLikvidusNameEnum.Mn]!!.value,
                            cu = percentMetalMap[TempLikvidusNameEnum.Cu]!!.value,
                            si = percentMetalMap[TempLikvidusNameEnum.Si]!!.value,
                            ti = percentMetalMap[TempLikvidusNameEnum.Ti]!!.value,
                            s = percentMetalMap[TempLikvidusNameEnum.S]!!.value,
                            p = percentMetalMap[TempLikvidusNameEnum.P]!!.value,
                            c = percentMetalMap[TempLikvidusNameEnum.C]!!.value,
                        ),
                        name = nameInput.text.toString(),
                        description = descriptionInput.text.toString()
                    )
                    if (vm.send(saveCalcEvent))
                    {
                        Toast.makeText(context, resources.getString(R.string.saved), Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(context, resources.getString(R.string.not_saved), Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .setNegativeButton(R.string.cancel) { _, _ -> }
            .create()
            .show()
    }

}