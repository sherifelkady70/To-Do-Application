package com.route.todo_application.ui

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.route.todo_application.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

    override fun onResume() {
        super.onResume()
        handleSelectionModes()
        Log.e("onResume in settings","done onResume in settings")
    }
    private fun handleSelectionModes(){
        val modes = arrayOf("Light","Dark")
        val adapter = ArrayAdapter(requireContext(),android.R.layout.simple_dropdown_item_1line,modes)
        binding.autoCompleteModeTv.setAdapter(adapter)
        binding.autoCompleteModeTv.setOnItemClickListener { parent, view, position, id ->
            when(position){
                0 -> {
                    toggleThemeForLight(this)
                 //   binding.autoCompleteModeTv.setText(modes[0])
                }
                1 ->{
                    toggleThemeForDark(this)
//                    binding.autoCompleteModeTv.setText(modes[1])
                }
            }
        }
    }

   private  fun toggleThemeForLight(fragment: Fragment) {
        val currentLightMode = fragment.requireActivity().resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        val newLightMode = if(currentLightMode == Configuration.UI_MODE_NIGHT_YES){
            AppCompatDelegate.MODE_NIGHT_NO
        }else{
            AppCompatDelegate.MODE_NIGHT_NO
        }
        AppCompatDelegate.setDefaultNightMode(newLightMode)
    }
    private  fun toggleThemeForDark(fragment: Fragment){
        val currentNightMode = fragment.requireActivity().resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        val newNightMode = if (currentNightMode == Configuration.UI_MODE_NIGHT_NO) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_YES
        }
        AppCompatDelegate.setDefaultNightMode(newNightMode)
    }
}