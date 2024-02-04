package com.example.engineersmetalcalcs.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.engineersmetalcalcs.R
import com.example.engineersmetalcalcs.adapters.ChapterAdapter
import com.example.engineersmetalcalcs.databinding.FragmentCastingMenuBinding
import com.example.engineersmetalcalcs.renderDto.Chapter

class CastingMenuFragment : Fragment() {
    private lateinit var binding: FragmentCastingMenuBinding
    private val adapter = ChapterAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCastingMenuBinding.inflate(layoutInflater)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
            val chapter = Chapter("Bla-bla-bla")
            adapter.addChapter(chapter)
        }
    }
}