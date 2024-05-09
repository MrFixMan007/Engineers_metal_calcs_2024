package calculation

import domain.model.volume.VolumeMeasure
import domain.model.volume.VolumeUnit
import domain.usecase.weightCargoWithoutRods.param.WeightOfCargoWithoutRodsParam
import org.junit.Assert.assertEquals
import org.junit.Test


class CalcWeightOfCargoWithoutRodsTest {

    private val cargoIngot = CalcWeightOfCargoWithoutRods()

    @Test
    fun `must return 0,91 tons`() {
        val volume = VolumeUnit(VolumeMeasure.M3, 0.1f)
        val param = WeightOfCargoWithoutRodsParam(volume)

        assertEquals(0.91f, cargoIngot.invoke(param).t())
    }

    @Test
    fun `must return 910 kg`() {
        val volume = VolumeUnit(VolumeMeasure.M3, 0.1f)
        val param = WeightOfCargoWithoutRodsParam(volume)

        assertEquals(910f, cargoIngot.invoke(param).kg())
    }

    @Test
    fun `must return 8,19 tons`() {
        val volume = VolumeUnit(VolumeMeasure.M3, 0.9f)
        val param = WeightOfCargoWithoutRodsParam(volume)

        val answer = cargoIngot.invoke(param).t()
        assertEquals(8.19f, Round.round(answer, 2))
    }

    @Test
    fun `must return 8,281 tons`() {
        val volume = VolumeUnit(VolumeMeasure.Cm3, 910000f)
        val param = WeightOfCargoWithoutRodsParam(volume)

        val answer = cargoIngot.invoke(param).t()
        assertEquals(8.281f, Round.round(answer, 3))
    }

    @Test
    fun `must return 8,327 tons`() {
        val volume = VolumeUnit(VolumeMeasure.Cm3, 915000f)
        val param = WeightOfCargoWithoutRodsParam(volume)

        val answer = cargoIngot.invoke(param).t()
        assertEquals(8.327f, Round.round(answer, 3))
    }

}
