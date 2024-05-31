package presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import calculation.Round
import data.model.CalcSave
import data.model.CalcType
import data.model.TypeEnum
import data.model.calcNamesEnum.TempLikvidusNameEnum
import data.model.container.TempLikvidusFasonContainer
import domain.usecase.saveCalc.SaveCalcUseCase
import domain.usecase.temperatureFason.TemperatureFasonUseCase
import domain.usecase.temperatureFason.inputParam.TemperatureFasonInputParam
import domain.usecase.temperatureFason.outputParam.TemperatureFasonOutputParam
import presentation.model.PercentMetalModel

class TempLikvidusFasonSavedViewModel(
    private val calcUseCase: TemperatureFasonUseCase,
    private val saveCalcUseCase: SaveCalcUseCase,
    private val countRound: Int,
) : ViewModel() {

    private val _resultLiveMutable = MutableLiveData<TemperatureFasonOutputParam>()
    val resultLive: LiveData<TemperatureFasonOutputParam> = _resultLiveMutable

    private val _savedValuesMutableMap = mutableMapOf<TempLikvidusNameEnum, PercentMetalModel>()

    init {
        if(!GlobalParameter.saveIsEmpty()) {
            val calcSave = GlobalParameter.getCalcSave()!!
            val container = (calcSave.container as TempLikvidusFasonContainer)
            _savedValuesMutableMap[TempLikvidusNameEnum.W] = PercentMetalModel(name = TempLikvidusNameEnum.W.name, value = container.w)
            _savedValuesMutableMap[TempLikvidusNameEnum.Cr] =  PercentMetalModel(name = TempLikvidusNameEnum.Cr.name, value = container.cr)
            _savedValuesMutableMap[TempLikvidusNameEnum.Co] =  PercentMetalModel(name = TempLikvidusNameEnum.Co.name, value = container.co)
            _savedValuesMutableMap[TempLikvidusNameEnum.Mo] =  PercentMetalModel(name = TempLikvidusNameEnum.Mo.name, value = container.mo)
            _savedValuesMutableMap[TempLikvidusNameEnum.V] = PercentMetalModel(name = TempLikvidusNameEnum.V.name, value = container.v)
            _savedValuesMutableMap[TempLikvidusNameEnum.Al] =  PercentMetalModel(name = TempLikvidusNameEnum.Al.name, value = container.al)
            _savedValuesMutableMap[TempLikvidusNameEnum.Ni] =  PercentMetalModel(name = TempLikvidusNameEnum.Ni.name, value = container.ni)
            _savedValuesMutableMap[TempLikvidusNameEnum.Mn] =  PercentMetalModel(name = TempLikvidusNameEnum.Mn.name, value = container.mn)
            _savedValuesMutableMap[TempLikvidusNameEnum.Cu] =  PercentMetalModel(name = TempLikvidusNameEnum.Cu.name, value = container.cu)
            _savedValuesMutableMap[TempLikvidusNameEnum.Si] =  PercentMetalModel(name = TempLikvidusNameEnum.Si.name, value = container.si)
            _savedValuesMutableMap[TempLikvidusNameEnum.Ti] =  PercentMetalModel(name = TempLikvidusNameEnum.Ti.name, value = container.ti)
            _savedValuesMutableMap[TempLikvidusNameEnum.S] = PercentMetalModel(name = TempLikvidusNameEnum.S.name, value = container.s)
            _savedValuesMutableMap[TempLikvidusNameEnum.P] = PercentMetalModel(name = TempLikvidusNameEnum.P.name, value = container.p)
            _savedValuesMutableMap[TempLikvidusNameEnum.C] = PercentMetalModel(name = TempLikvidusNameEnum.C.name, value = container.c)
        }
    }

    fun getSavedValuesMap(): MutableMap<TempLikvidusNameEnum, PercentMetalModel>{
        return _savedValuesMutableMap.mapValues { it.value }.toMutableMap()
    }

    private fun calc(param: TemperatureFasonInputParam){
        val res = Round.invoke(calcUseCase.invoke(param), countRound)
        _resultLiveMutable.value = TemperatureFasonOutputParam(
            res = res.res,
            resLower = res.resLower,
            resUpper = res.resUpper,
            resLowerInFurnace = res.resLowerInFurnace,
            resUpperInFurnace = res.resUpperInFurnace,
        )
    }

    private suspend fun save(param: TemperatureFasonInputParam, name: String, description: String) : Boolean{
        val result = Round.invoke(calcUseCase.invoke(param), countRound)

        return saveCalcUseCase.invoke(
            CalcSave(
            type = CalcType(TypeEnum.TemperatureFason),
            name = name,
            description = description,
            container = TempLikvidusFasonContainer(
                w = param.w,
                cr = param.cr,
                co = param.co,
                mo = param.mo,
                v = param.v,
                al = param.al,
                ni = param.ni,
                mn = param.mn,
                cu = param.cu,
                si = param.si,
                ti = param.ti,
                s = param.s,
                p = param.p,
                c = param.c,
                res = result.res
                )
            )
        )
    }
    suspend fun send(event: TempLikvidusFasonEvent) : Boolean{
        when (event){
            is SaveCalcTempLikvidFasonEvent -> {
                return save(event.param, event.name, event.description)
            }
            is CalcFasonEvent -> {
                calc(param = event.param)
            }
        }
        return false
    }
}