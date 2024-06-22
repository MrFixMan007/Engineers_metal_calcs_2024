package presentation.adapters

import android.text.InputType
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.PopupMenu
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import data.CalcSaveRepository
import data.model.SimpleCalcInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import metalcalcs.core_ui.R
import metalcalcs.feature_listing_all_saves.databinding.SaveListItemBinding
import presentation.model.SaveItem


class SaveAdapter(private val listener: Listener,
                  private val calcSaveRepository: CalcSaveRepository): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val saves = mutableListOf<SaveItem>()
    class SaveHolder(item: View,
                     private val calcSaveRepository: CalcSaveRepository): RecyclerView.ViewHolder(item){
        private val binding = SaveListItemBinding.bind(itemView)
        fun bind(saveItem: SaveItem, listener: Listener) = with(binding){
            name.text = saveItem.name
            description.text = saveItem.description
            date.text = saveItem.date
            result.text = saveItem.result

            more.setOnClickListener {
                showPopupMenu(itemView, listener, saveItem)
            }
            root.setOnClickListener{
                listener.onClick(saveItem)
            }
        }
        private fun showPopupMenu(v: View, listener: Listener, saveItem: SaveItem) {
            val view = LayoutInflater.from(v.context).inflate(metalcalcs.feature_listing_all_saves.R.layout.custom_popup_item, binding.root)

            val popupMenu = PopupMenu(v.context, view, Gravity.END)
            popupMenu.inflate(metalcalcs.feature_listing_all_saves.R.menu.popupmenu)
            popupMenu.setOnMenuItemClickListener { item: MenuItem ->
                when (item.itemId) {
                    metalcalcs.feature_listing_all_saves.R.id.rename -> callRenameItemWindow(saveItem, listener)
                    metalcalcs.feature_listing_all_saves.R.id.change_description -> callChangeDescriptionWindow(saveItem, listener)
                    metalcalcs.feature_listing_all_saves.R.id.remove -> callRemoveItemWindow(saveItem, listener)
                }
                true
            }
            popupMenu.show()
        }

        private fun callRemoveItemWindow(saveItem: SaveItem, listener: Listener) {
            val builder = AlertDialog.Builder(binding.root.context)

            builder.setTitle(R.string.remove_item_window)
            builder.setPositiveButton(
                R.string.yes
            ) { _, _ -> deleteSave(saveItem, listener)}
            builder.setNegativeButton(
                R.string.no
            ) { _, _ -> }
            builder.show()
        }

        private fun callChangeDescriptionWindow(saveItem: SaveItem, listener: Listener) {
            val builder = AlertDialog.Builder(binding.root.context)

            builder.setTitle(R.string.change_description_item_window)
            val descriptionInput = EditText(binding.root.context)
            descriptionInput.inputType = InputType.TYPE_CLASS_TEXT
            descriptionInput.setText(saveItem.description)
            builder.setView(descriptionInput)
            builder.setPositiveButton(
                R.string.ready
            ) { _, _ -> changeDescription(saveItem, listener, descriptionInput.text.toString()) }
            builder.setNegativeButton(
                R.string.cancel
            ) { _, _ -> }
            builder.show()
        }

        private fun callRenameItemWindow(saveItem: SaveItem, listener: Listener) {
            val builder = AlertDialog.Builder(binding.root.context)

            builder.setTitle(R.string.rename_item_window)
            val nameInput = EditText(binding.root.context)
            nameInput.inputType = InputType.TYPE_CLASS_TEXT
            nameInput.setText(saveItem.name)
            builder.setView(nameInput)
            builder.setPositiveButton(
                R.string.ready
            ) { _, _ -> changeName(saveItem, listener, nameInput.text.toString()) }
            builder.setNegativeButton(
                R.string.cancel
            ) { _, _ -> }
            builder.show()
        }

        private fun changeDescription(saveItem: SaveItem, listener: Listener, newDescription: String) {
            if(saveItem.description != newDescription)
            {
                val oldDescription = saveItem.description
                saveItem.description = newDescription
                listener.itemWasChanged(saveItem)

                CoroutineScope(Dispatchers.IO).launch {
                    calcSaveRepository.setCalcSaveDescription(
                        SimpleCalcInfo(
                            name = saveItem.name,
                            description = oldDescription,
                            date = saveItem.date,
                            result = saveItem.result
                        ), newDescription
                    )
                }
            }
        }

        private fun changeName(saveItem: SaveItem, listener: Listener, newName: String) {
            if(saveItem.name != newName){
                val oldName = saveItem.name
                saveItem.name = newName
                listener.itemWasChanged(saveItem)

                CoroutineScope(Dispatchers.IO).launch {
                    calcSaveRepository.setCalcSaveName(
                        SimpleCalcInfo(
                            name = oldName,
                            description = saveItem.description,
                            date = saveItem.date,
                            result = saveItem.result
                        ), newName
                    )
                }
            }
        }
        private fun deleteSave(saveItem: SaveItem, listener: Listener){
            listener.deleteSave(saveItem)

            CoroutineScope(Dispatchers.IO).launch {
                calcSaveRepository.deleteCalcSave(
                    SimpleCalcInfo(
                        name = saveItem.name,
                        description = saveItem.description,
                        date = saveItem.date,
                        result = saveItem.result
                    )
                )
            }
        }
    }

    fun addAll(items: List<SaveItem>){
        if (saves.addAll(items))
            notifyItemRangeInserted(0, items.size)
    }
    fun add(item: SaveItem){
        if (saves.add(item))
            notifyItemRangeInserted(saves.size - 1, 1)
    }
    fun deleteItem(item: SaveItem) {
        val index = saves.indexOf(item)
        saves.remove(item)
        notifyItemRemoved(index)
    }

    fun itemWasChanged(item: SaveItem) {
        notifyItemChanged(saves.indexOf(item))
    }

    interface Listener{
        fun onClick(item: SaveItem)
        fun deleteSave(item: SaveItem)
        fun itemWasChanged(item: SaveItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(metalcalcs.feature_listing_all_saves.R.layout.save_list_item, parent, false)
        return SaveHolder(view, calcSaveRepository)
    }

    override fun getItemCount(): Int {
        return saves.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SaveHolder -> {
                holder.bind(saves[position], listener)
            }
        }
    }
}