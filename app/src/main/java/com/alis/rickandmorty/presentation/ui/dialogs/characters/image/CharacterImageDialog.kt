package com.alis.rickandmorty.presentation.ui.dialogs.characters.image

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.viewbinding.library.dialogfragment.viewBinding
import androidx.navigation.fragment.navArgs
import coil.load
import com.alis.rickandmorty.common.base.BaseDialog
import com.alis.rickandmorty.databinding.DialogCharacterImageBinding

class CharacterImageDialog : BaseDialog<DialogCharacterImageBinding>() {

    override val binding: DialogCharacterImageBinding by viewBinding()
    private val args: CharacterImageDialogArgs by navArgs()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    override fun initialize() {
        binding.imageCharactersPhotoDetail.load(args.image)
    }
}