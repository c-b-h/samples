package se.ingenuity.samples.feature.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import se.ingenuity.samples.databinding.FragmentQuizBinding

class QuizFragment : Fragment() {
    private lateinit var binding: FragmentQuizBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuizBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = ViewModelProvider(this)[QuizViewModel::class.java]

        binding.lifecycleOwner = viewLifecycleOwner
    }
}