package se.ingenuity.samples.feature.map

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import se.ingenuity.samples.R
import se.ingenuity.samples.databinding.FragmentMapBinding
import se.ingenuity.samples.feature.common.app.GenericDialogFragmentArgs

class MapFragment : Fragment() {
    private lateinit var binding: FragmentMapBinding

    private val viewModel: MapViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val identifier = Integer.toHexString(System.identityHashCode(this))
        Log.d(TAG, "Fragment $identifier's onCreate() reached")

        val navController = findNavController()
        val navBackStackEntry = navController.currentBackStackEntry!!
        navBackStackEntry.lifecycle.addObserver(LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                with(navBackStackEntry.savedStateHandle) {
                    if (contains(SAVED_STATE_HANDLE__ANY_KEY)) {
                        if (this@MapFragment.isAdded) {
                            Log.d(TAG, "Fragment $identifier contains $SAVED_STATE_HANDLE__ANY_KEY")
                        } else {
                            Log.wtf(
                                TAG,
                                "Fragment $identifier contains $SAVED_STATE_HANDLE__ANY_KEY (detached!)"
                            )
                        }

                        remove<Any>(SAVED_STATE_HANDLE__ANY_KEY)
                        viewModel.endRide(true)
                    }
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navController = findNavController()

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        viewModel.wrappedEvent.observe(viewLifecycleOwner, Observer { event ->
            when (event.getContentIfNotHandled()) {
                is MapEvent.EndConfirmation -> navController.navigate(
                    R.id.fragment__generic_dialog,
                    GenericDialogFragmentArgs(SAVED_STATE_HANDLE__ANY_KEY).toBundle()
                )
            }
        })
    }

    private companion object {
        private const val TAG = "MapFragment"
        private const val SAVED_STATE_HANDLE__ANY_KEY = "SAVED_STATE_HANDLE__ANY_KEY"
    }
}
