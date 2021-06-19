package com.alis.rickandmorty.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alis.rickandmorty.databinding.DialogFilterBinding

class FilterDialog : AppCompatDialogFragment() {

    private var _binding: DialogFilterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogFilterBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(activity)
            .setView(binding.root)
            .create()

        setupListeners()

        return builder
    }

    private fun setupListeners() {
        binding.textFilterDialog.setOnClickListener {
            Toast.makeText(context, "TOAST", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}