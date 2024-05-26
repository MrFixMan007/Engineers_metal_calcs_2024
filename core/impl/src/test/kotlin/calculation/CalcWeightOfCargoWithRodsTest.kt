package calculation

import domain.model.volume.VolumeUnit
import domain.usecase.weightCargoWithRods.inputParam.WeightOfCargoWithRodsParam
import org.junit.Assert.assertEquals
import org.junit.Test

class CalcWeightOfCargoWithRodsTest{

    private val cargoIngot = CalcWeightOfCargoWithRods()

    @Test
    fun `must return 0,64 tons`(){
        val vDo = VolumeUnit.m3(0.07f)
        val vBez = VolumeUnit.m3(0.00025f)
        val param = WeightOfCargoWithRodsParam(vDo = vDo, vBez = vBez)
        assertEquals(0.64f, Round.round(cargoIngot.invoke(param).t(), 2))
    }

    @Test
    fun `must return 638,6 kg`(){
        val vDo = VolumeUnit.m3(0.07f)
        val vBez = VolumeUnit.m3(0.00025f)
        val param = WeightOfCargoWithRodsParam(vDo = vDo, vBez = vBez)
        assertEquals(638.6f, Round.round(cargoIngot.invoke(param).kg(), 1))
    }

    @Test
    fun `must return 1,25 tons`(){
        val vDo = VolumeUnit.m3(0.12f)
        val vBez = VolumeUnit.m3(0.0248f)
        val param = WeightOfCargoWithRodsParam(vDo = vDo, vBez = vBez)
        assertEquals(1.25f, Round.round(cargoIngot.invoke(param).t(), 2))
    }
    @Test
    fun `must return 1253,2 kg`(){
        val vDo = VolumeUnit.m3(0.12f)
        val vBez = VolumeUnit.m3(0.0248f)
        val param = WeightOfCargoWithRodsParam(vDo = vDo, vBez = vBez)
        assertEquals(1253.2f, Round.round(cargoIngot.invoke(param).kg(), 2))
    }
    @Test
    fun `must return 0,45 tons`(){
        val vDo = VolumeUnit.m3(0.0012f)
        val vBez = VolumeUnit.m3(0.068f)
        val param = WeightOfCargoWithRodsParam(vDo = vDo, vBez = vBez)
        assertEquals(0.45f, Round.round(cargoIngot.invoke(param).t(), 2))
    }
}