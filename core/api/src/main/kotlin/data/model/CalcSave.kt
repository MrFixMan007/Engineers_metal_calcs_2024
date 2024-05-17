package data.model

data class CalcSave (val type: CalcType,
                     val name: String = "",
                     val description: String = "",
                     val date: String = "",
                     val container: Container,
                     val result: String = container.getResult(),
    )