package domain.usecase.temperatureIngot.outputParam

data class TemperatureIngotOutputParam(
    val res: Float,
    val resLower: Float,
    val resUpper: Float,
    val resLowerInFurnace: Float,
    val resUpperInFurnace: Float,
)
