package domain.usecase.temperatureFason.outputParam

data class TemperatureFasonOutputParam(
    val res: Float,
    val resLower: Float,
    val resUpper: Float,
    val resLowerInFurnace: Float,
    val resUpperInFurnace: Float,
)
