package presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import metalcalcs.core_ui.R
import metalcalcs.feature_listing_all_calcs.impl.databinding.FragmentCastingMenuBinding
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named
import presentation.adapter.ChapterAdapter
import presentation.model.Chapter

class CastingMenuFragment : Fragment(), ChapterAdapter.Listener, MenuProvider {
    private lateinit var binding: FragmentCastingMenuBinding
    private val adapter = ChapterAdapter(this)
    private val chapters: List<Chapter> by inject((named("chapterList")))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCastingMenuBinding.inflate(layoutInflater)
        init()
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
    }

    private fun init(){
        binding.apply {
            rcViewCastChapters.layoutManager = LinearLayoutManager(context)
            rcViewCastChapters.adapter = adapter
            adapter.addAll(chapters)
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.toolbar_empty_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when(menuItem.itemId){
            android.R.id.home -> findNavController().popBackStack()
        }
        return true
    }

    override fun onClick(item: Chapter) {
        findNavController().navigate(item.resIdWhereNavigate)
    }
}