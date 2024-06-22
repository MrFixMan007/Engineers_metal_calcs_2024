package di

import android.content.Context
import metalcalcs.feature_listing_all_calcs.impl.R
import org.koin.core.qualifier.named
import org.koin.dsl.module
import presentation.adapters.Chapter

val casting_menu_module = module {
    single <List<Chapter>>(named("chapterList")) {
        listOf(
            Chapter(
                title = get<Context>().resources.getString(metalcalcs.core_ui.R.string.calc_weight_of_the_cargo),
                resIdWhereNavigate = R.id.action_castingMenuFragment_to_cargo_weight_nav
            ),
        )
    }
}