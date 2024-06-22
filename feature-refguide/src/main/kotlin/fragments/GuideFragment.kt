package fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.feature_refguide.databinding.FragmentGuideBinding
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named
import presentation.adapters.ImageText
import presentation.adapters.ImageTextAdapter


class GuideFragment : Fragment() {

    private lateinit var binding: FragmentGuideBinding

    private lateinit var adapter : ImageTextAdapter
    private val imageTextList: List<ImageText> by inject(named("gost_3_1125_88"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentGuideBinding.inflate(layoutInflater)

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
        adapter = ImageTextAdapter()

        binding.apply {
            rcView.adapter = adapter
            rcView.layoutManager = LinearLayoutManager(context)
            adapter.addAll(imageTextList)
        }
    }
}