package fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.feature_refguide.databinding.FragmentGuideBinding
import org.koin.android.ext.android.get
import org.koin.core.qualifier.named
import presentation.adapters.ImageText
import presentation.adapters.ImageTextAdapter


class GuideFragment : Fragment() {

    private lateinit var binding: FragmentGuideBinding

    private lateinit var adapter : ImageTextAdapter
    private lateinit var imageTextList: List<ImageText>


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
        (activity as? AppCompatActivity)?.supportActionBar?.title = arguments?.getString("title")

        adapter = ImageTextAdapter()

        imageTextList = get(named(arguments?.getString("nameOfGost")!!))

        binding.apply {
            rcView.adapter = adapter
            rcView.layoutManager = LinearLayoutManager(context)
            adapter.addAll(imageTextList)
        }
    }
}