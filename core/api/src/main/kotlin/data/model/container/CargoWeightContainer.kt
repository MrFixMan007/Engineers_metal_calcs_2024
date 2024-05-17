package data.model.container

import data.model.Container
import domain.model.volume.VolumeUnit
import domain.model.weight.WeightUnit

data class CargoWeightContainer(
    val vB: VolumeUnit,
    val v1C: VolumeUnit,
    val v2C: VolumeUnit,
    val mB: WeightUnit,
    val mC: WeightUnit,
) : Container {
    override fun getResult(): String {
        return "${mB.value} ${mC.value}"
    }
}
