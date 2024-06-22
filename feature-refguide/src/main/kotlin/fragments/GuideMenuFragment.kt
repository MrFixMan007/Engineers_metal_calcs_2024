package fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.feature_refguide.databinding.FragmentGuideMenuBinding
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named
import presentation.adapters.Chapter
import presentation.adapters.ChapterAdapter


class GuideMenuFragment : Fragment(), ChapterAdapter.Listener {

    private lateinit var binding: FragmentGuideMenuBinding

    private val adapter = ChapterAdapter(this)
    private val chapters: List<Chapter> by inject(named("chapters"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentGuideMenuBinding.inflate(layoutInflater)

        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun init() {

        binding.apply {
            rcView.adapter = adapter
            rcView.layoutManager = LinearLayoutManager(context)
            adapter.addAll(chapters)
        }
    }

    override fun onClick(item: Chapter) {
        findNavController().navigate(
            item.resIdWhereNavigate,
            bundleOf(
                "title" to item.title,
                "nameOfGost" to item.nameOfType,
            )
        )
    }
}