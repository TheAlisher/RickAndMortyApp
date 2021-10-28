package com.alis.rickandmorty.presentation.ui.fragments.characters.photodetail

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.alis.rickandmorty.databinding.DialogCharactersPhotoDetailBinding

class CharactersPhotoDetailDialog : AppCompatDialogFragment() {

    private var _binding: DialogCharactersPhotoDetailBinding? = null
    private val binding get() = _binding!!
    private val args: CharactersPhotoDetailDialogArgs by navArgs()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogCharactersPhotoDetailBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(activity)
            .setView(binding.root)
            .create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        setupViews()

        return dialog
    }

    private fun setupViews() {
        binding.imageCharactersPhotoDetail.load(args.image)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}