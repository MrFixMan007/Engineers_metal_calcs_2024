package presentation.viewmodel

import androidx.lifecycle.ViewModel
import data.model.CalcSave

class SavesViewModel(
    val allCalcSaves: List<CalcSave>
) : ViewModel() {

}