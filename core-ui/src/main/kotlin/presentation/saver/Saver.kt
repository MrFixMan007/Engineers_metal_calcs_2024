package presentation.saver

import android.content.Context
import android.text.InputType
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import metalcalcs.core_ui.R

//TODO: хз как сделать общим классом для страниц т.к. требует appTheme
class Saver(val context: Context) {
    fun invoke() {
        val nameBuilder = AlertDialog.Builder(context)
        nameBuilder.setTitle(R.string.name_item_window)
        val nameInput = EditText(context)
        nameInput.inputType = InputType.TYPE_CLASS_TEXT
        nameBuilder.setView(nameInput)

        nameBuilder.setPositiveButton(
            R.string.ready
        ) { _, _ ->
            val descriptionBuilder = AlertDialog.Builder(context)
            descriptionBuilder.setTitle(R.string.description_item_window)
            val descriptionInput = EditText(context)
            descriptionInput.inputType = InputType.TYPE_CLASS_TEXT
            descriptionBuilder.setView(descriptionInput)

            descriptionBuilder.setPositiveButton(
                R.string.ready
            ) { _, _ ->
                CoroutineScope(Dispatchers.Main).launch {
                    Toast.makeText(context, context.resources.getString(R.string.not_saved), Toast.LENGTH_SHORT).show()
//                    if(saveServiceCargoWeight.set(nameInput.text.toString(), descriptionInput.text.toString())){
//                        Toast.makeText(context, context.resources.getString(R.string.saved), Toast.LENGTH_SHORT).show()
//                    }
//                    else Toast.makeText(context, context.resources.getString(R.string.not_saved), Toast.LENGTH_SHORT).show()
                }
            }

            descriptionBuilder.setNegativeButton(
                R.string.cancel
            ) { _, _ -> }
            descriptionBuilder.show()
        }

        nameBuilder.setNegativeButton(
            R.string.cancel
        ) { _, _ -> }
        nameBuilder.show()
    }
}