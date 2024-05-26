package presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.navigation.fragment.findNavController
import metalcalcs.feature_listing_all_calcs.impl.R
import metalcalcs.feature_listing_all_calcs.impl.databinding.FragmentLikvidTempMenuBinding

class LikvidTempMenuFragment : Fragment(), MenuProvider {
    private lateinit var binding: FragmentLikvidTempMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentLikvidTempMenuBinding.inflate(layoutInflater)
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
            likvidCalcMenuButton1.setOnClickListener {
                controller.navigate(R.id.action_likvidTempMenuFragment_to_fason_nav)
            }
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(metalcalcs.core_ui.R.menu.toolbar_main_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when(menuItem.itemId){
            metalcalcs.core_ui.R.id.turnOff -> {
                activity?.finish()
            }
        }
        return true
    }
}