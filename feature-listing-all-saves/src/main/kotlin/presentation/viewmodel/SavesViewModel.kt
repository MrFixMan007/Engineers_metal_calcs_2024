package presentation.viewmodel

import androidx.lifecycle.ViewModel
import data.CalcSaveRepository
import data.model.CalcSave

class SavesViewModel(
    val allCalcSaves: MutableList<CalcSave>,
    val calcSaveRepository: CalcSaveRepository,
) : ViewModel() {
    suspend fun update(){
        val updatedCalcSaves = calcSaveRepository.getAllCalcSaves()
        allCalcSaves.clear()
        allCalcSaves.addAll(updatedCalcSaves)
    }
}