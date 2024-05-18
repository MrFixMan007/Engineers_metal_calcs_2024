package di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import presentation.viewmodel.SavesViewModel

val feature_saved_calcs_module = module {
    viewModel<SavesViewModel> {
        SavesViewModel(
            allCalcSaves = get(named("calcSaves"))
            )
    }
}