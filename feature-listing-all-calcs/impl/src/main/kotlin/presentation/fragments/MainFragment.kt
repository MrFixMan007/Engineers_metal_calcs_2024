package presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.navigation.fragment.findNavController
import metalcalcs.core_ui.R
import metalcalcs.feature_listing_all_calcs.impl.databinding.FragmentMainBinding

class MainFragment : Fragment(), MenuProvider {
    private lateinit var binding: FragmentMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMainBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().addMenuProvider(this, viewLifecycleOwner)
        val controller = findNavController()

        binding.apply {
            mainMenuButton1.setOnClickListener {
                controller.navigate(metalcalcs.feature_listing_all_calcs.impl.R.id.action_mainFragment_to_likvidTempMenuFragment)
            }
            mainMenuButton2.setOnClickListener {
                controller.navigate(metalcalcs.feature_listing_all_calcs.impl.R.id.action_mainFragment_to_castingMenuFragment)
            }
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.toolbar_main_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when(menuItem.itemId){
            R.id.turnOff -> {
                activity?.finish() //TODO: закрывает, но на фоне остается
                Log.i("fg", "Main1 ЛОГ")
            }
        }
        return true
    }
}