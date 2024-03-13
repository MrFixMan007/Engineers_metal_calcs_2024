package com.example.engineersmetalcalcs.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.engineersmetalcalcs.R
import com.example.engineersmetalcalcs.adapters.ChapterAdapter
import com.example.engineersmetalcalcs.databinding.FragmentCastingMenuBinding
import com.example.engineersmetalcalcs.listItem.Chapter

class CastingMenuFragment : Fragment(), ChapterAdapter.Listener {
    private lateinit var binding: FragmentCastingMenuBinding
    private val adapter = ChapterAdapter(this)
    private lateinit var chapters: List<Chapter>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCastingMenuBinding.inflate(layoutInflater)
        chapters = getChapters()
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
    }

    private fun init(){
        binding.apply {
            rcViewCastChapters.layoutManager = LinearLayoutManager(context)
            rcViewCastChapters.adapter = adapter
            adapter.addAll(chapters)
        }
    }

    override fun onClick(item: Chapter) {
        findNavController().navigate(item.resId)
    }

    private fun getChapters(): List<Chapter>{
        return listOf(
            Chapter(resources.getString(R.string.calc_weight_of_the_cargo), R.id.action_castingMenuFragment_to_cargoWeightFragment)
        )
    }
}