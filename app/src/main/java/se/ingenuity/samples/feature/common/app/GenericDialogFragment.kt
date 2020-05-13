package se.ingenuity.samples.feature.common.app

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class GenericDialogFragment : AppCompatDialogFragment() {
    private val args by navArgs<GenericDialogFragmentArgs>()

    private lateinit var previousSavedStateHandle: SavedStateHandle

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        previousSavedStateHandle = findNavController().previousBackStackEntry!!.savedStateHandle

        return with(AlertDialog.Builder(requireContext())) {
            setPositiveButton("Positive text") { _, _ ->
                previousSavedStateHandle.set(args.onPositiveButtonEventKey, 666)
            }

            create()
        }
    }
}