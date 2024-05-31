package presentation.model

import data.model.CalcType

data class SaveItem(
    var name: String,
    var date: String,
    var description: String,
    var result: String,
    var type: CalcType
)
