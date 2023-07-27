package ru.studyguk.composition.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.studyguk.composition.R
import ru.studyguk.composition.databinding.FragmentGameFinishedBinding
import ru.studyguk.composition.domain.entity.GameResult

class GameFinishedFragment : Fragment() {

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding == null")

    private val args by navArgs<GameFinishedFragmentArgs>()

    private fun retryGame() {
        findNavController().popBackStack()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
        bindViews()
    }

    private fun bindViews() {
        with(binding) {
            emojiResult.setImageResource(getSmileResId())
            tvRequiredAnswers.text = String.format(
                resources.getString(R.string.requireRightAnswersText),
                args.gameResult.gameSettings.minCountOfRightAnswers
            )
            tvScoreAnswers.text = String.format(
                resources.getString(R.string.scoreResultText),
                args.gameResult.countOfRightAnswers
            )
            tvRequiredPercentage.text = String.format(
                resources.getString(R.string.requireRightAnswersPercentageText),
                args.gameResult.gameSettings.minPercentOfRightAnswers
            )
            tvScorePercentage.text = String.format(
                resources.getString(R.string.scoreRightAnswersPercentage),
                (((args.gameResult.countOfRightAnswers / args.gameResult.countOfQuestions.toDouble()) * 100).toInt()).toString()
            )
        }
    }


    private fun getSmileResId(): Int {
        return if (args.gameResult.winner) {
            R.drawable.ic_smile
        } else {
            R.drawable.ic_sad
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}