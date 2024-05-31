package data.model.container

import data.model.Container

data class TempLikvidusIngotContainer(
    val w: Float = 0f,
    val cr: Float = 0f,
    val co: Float = 0f,
    val mo: Float = 0f,
    val v: Float = 0f,
    val al: Float = 0f,
    val ni: Float = 0f,
    val mn: Float = 0f,
    val cu: Float = 0f,
    val si: Float = 0f,
    val ti: Float = 0f,
    val s: Float = 0f,
    val p: Float = 0f,
    val c: Float = 0f,
    val res: Float,
) : Container {
    override fun getResult(): String {
        return "$res"
    }
}
