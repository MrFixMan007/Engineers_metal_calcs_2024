package di

import com.example.feature_refguide.R
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import presentation.adapters.Chapter
import presentation.adapters.ImageText

val feature_refguide_module = module {

    val gost1 = "gost_3_1125_88"

    single <List<ImageText>>(named(gost1)) {
        val stringArray = androidContext().resources.getStringArray(R.array.gost_3_1125_88)
        listOf(
            ImageText(text = stringArray[0]),
            ImageText(text = stringArray[1]),
            ImageText(text = stringArray[2]),
            ImageText(imageId = R.drawable.gost_3_1125_88_arrow),
            ImageText(text = stringArray[3]),
            ImageText(text = stringArray[4], imageId = R.drawable.gost_3_1125_88_drawing_1),
            ImageText(text = stringArray[5], imageId = R.drawable.gost_3_1125_88_drawing_2),
            ImageText(text = stringArray[6]),
            ImageText(text = stringArray[7], imageId = R.drawable.gost_3_1125_88_drawing_3),
            ImageText(text = stringArray[8], imageId = R.drawable.gost_3_1125_88_drawing_4),
            ImageText(text = stringArray[9]),
            ImageText(text = stringArray[10], imageId = R.drawable.gost_3_1125_88_drawing_5),
            ImageText(text = stringArray[11]),
            ImageText(text = stringArray[12]),
            ImageText(text = stringArray[13]),
            ImageText(text = stringArray[14], imageId = R.drawable.gost_3_1125_88_drawing_6),
            ImageText(text = stringArray[15], imageId = R.drawable.gost_3_1125_88_drawing_7),
            ImageText(text = stringArray[16]),
            ImageText(text = stringArray[17], imageId = R.drawable.gost_3_1125_88_drawing_8),
            ImageText(text = stringArray[18]),
            ImageText(text = stringArray[19]),
            ImageText(text = stringArray[20], imageId = R.drawable.gost_3_1125_88_drawing_9),
            ImageText(text = stringArray[21], imageId = R.drawable.gost_3_1125_88_drawing_10),
            ImageText(text = stringArray[22], imageId = R.drawable.gost_3_1125_88_drawing_11),
            ImageText(text = stringArray[23], imageId = R.drawable.gost_3_1125_88_drawing_12),
            ImageText(text = stringArray[24], imageId = R.drawable.gost_3_1125_88_drawing_13),
            ImageText(text = stringArray[25], imageId = R.drawable.gost_3_1125_88_drawing_14),
            ImageText(text = stringArray[26], imageId = R.drawable.gost_3_1125_88_drawing_15),
            ImageText(text = stringArray[27]),
            ImageText(text = stringArray[28]),
            ImageText(text = stringArray[29], imageId = R.drawable.gost_3_1125_88_drawing_16),
            ImageText(text = stringArray[30]),
            ImageText(text = stringArray[31]),
            ImageText(text = stringArray[32], imageId = R.drawable.gost_3_1125_88_drawing_17),
            ImageText(text = stringArray[33], imageId = R.drawable.gost_3_1125_88_drawing_18),
            ImageText(text = stringArray[34]),
            ImageText(text = stringArray[35], imageId = R.drawable.gost_3_1125_88_drawing_19),
            ImageText(text = stringArray[36]),
            ImageText(text = stringArray[37]),
            ImageText(text = stringArray[38], imageId = R.drawable.gost_3_1125_88_drawing_20),
            ImageText(text = stringArray[39]),
            ImageText(text = stringArray[40]),
            ImageText(text = stringArray[41]),
            ImageText(text = stringArray[42], imageId = R.drawable.gost_3_1125_88_drawing_21),
            ImageText(text = stringArray[43], imageId = R.drawable.gost_3_1125_88_drawing_22),
            ImageText(text = stringArray[44]),
            ImageText(text = stringArray[45]),
            ImageText(text = stringArray[46], imageId = R.drawable.gost_3_1125_88_drawing_23),
            ImageText(text = stringArray[47], imageId = R.drawable.gost_3_1125_88_drawing_24),
            ImageText(text = stringArray[48]),
            ImageText(text = stringArray[49], imageId = R.drawable.gost_3_1125_88_drawing_25),
            ImageText(text = stringArray[50], imageId = R.drawable.gost_3_1125_88_drawing_26),
            ImageText(text = stringArray[51]),
            ImageText(text = stringArray[52]),
            ImageText(imageId = R.drawable.gost_3_1125_88_table),
            ImageText(text = stringArray[53]),
            ImageText(text = stringArray[54]),
            ImageText(text = stringArray[55]),
            ImageText(text = stringArray[56]),
            ImageText(text = stringArray[57], imageId = R.drawable.gost_3_1125_88_drawing_27),
            ImageText(text = stringArray[58]),
            ImageText(text = stringArray[59], imageId = R.drawable.gost_3_1125_88_drawing_28),
            ImageText(text = stringArray[60]),
            ImageText(text = stringArray[61], imageId = R.drawable.gost_3_1125_88_drawing_29),
            ImageText(text = stringArray[62], imageId = R.drawable.gost_3_1125_88_drawing_30),
            ImageText(text = stringArray[63], imageId = R.drawable.gost_3_1125_88_drawing_31),
            ImageText(text = stringArray[64], imageId = R.drawable.gost_3_1125_88_drawing_32),
            ImageText(text = stringArray[65]),
            ImageText(imageId = R.drawable.gost_3_1125_88_example),
        )
    }

    single <List<Chapter>>(named("chapters")) {
        val resource = androidContext().resources
        listOf(
            Chapter(
                title = resource.getString(R.string.gost_3_1125_88_name),
                resIdWhereNavigate = R.id.action_guideMenuFragment_to_guideFragment,
                nameOfType = gost1
            ),
        )
    }
}