package com.route.todo_application.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.route.todo_application.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    lateinit var binding : FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleSelectionModes()

    }

    private fun handleSelectionModes(){
        val modes = arrayOf("Light","Dark")
        val adapter = ArrayAdapter(requireContext(),android.R.layout.simple_dropdown_item_1line,modes)
        binding.autoCompleteModeTv.setAdapter(adapter)
    }
}