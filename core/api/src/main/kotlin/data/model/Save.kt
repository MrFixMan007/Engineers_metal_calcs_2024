package data.model

import domain.model.calc.TypeEnum
import java.text.SimpleDateFormat
import java.util.Date

data class Save(
    val id: Long,
    val name: String,
    val date: String = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date()),
    val description: String = "",
    val result: String,
    val typeEnum: TypeEnum,
)
