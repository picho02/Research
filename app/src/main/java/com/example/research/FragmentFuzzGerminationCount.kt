package com.example.research

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.research.databinding.FragmentFuzzgerminationcountBinding


class FragmentFuzzGerminationCount : Fragment() {
    private lateinit var binding: FragmentFuzzgerminationcountBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFuzzgerminationcountBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spinnerData = resources.getStringArray(R.array.spinner_fuzzactivity)
        val opciones = ArrayAdapter<String>(requireContext(),android.R.layout.simple_spinner_dropdown_item,spinnerData)
        binding.spFuzzActivity.adapter=opciones
    }

}