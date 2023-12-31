package ru.studyguk.composition.presentation

import android.content.DialogInterface.OnClickListener
import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import ru.studyguk.composition.R
import ru.studyguk.composition.domain.entity.GameResult

interface OnOptionClickListener {
    fun onOptionClick(option: Int)
}

@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.resources.getString(R.string.requireRightAnswersText),
        count
    )
}

@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(textView: TextView, percentage: Int) {
    textView.text = String.format(
        textView.context.resources.getString(R.string.requireRightAnswersPercentageText),
        percentage
    )
}

@BindingAdapter("scoreAnswers")
fun bindScoreAnswers(textView: TextView, score: Int) {
    textView.text = String.format(
        textView.context.resources.getString(R.string.scoreResultText),
        score
    )
}

@BindingAdapter("scorePercentage")
fun bindScorePercentage(textView: TextView, gameResult: GameResult) {
    textView.text = String.format(
        textView.context.resources.getString(R.string.scoreRightAnswersPercentage),
        (((gameResult.countOfRightAnswers / gameResult.countOfQuestions.toDouble()) * 100).toInt()).toString()
    )
}

@BindingAdapter("emojiImage")
fun bindScorePercentage(imageView: ImageView, winner: Boolean) {
    imageView.setImageResource(getSmileResId(winner))
}

private fun getSmileResId(winner: Boolean): Int {
    return if (winner) {
        R.drawable.ic_smile
    } else {
        R.drawable.ic_sad
    }
}

@BindingAdapter("sumValue")
fun bindSumValue(textView: TextView, count: Int) {
    textView.text = count.toString()
}

@BindingAdapter("visibleNumberValue")
fun bindVisibleNumberValue(textView: TextView, count: Int) {
    textView.text = count.toString()
}

@BindingAdapter("progressAnswersColor")
fun bindProgressAnswersColor(textView: TextView, enoughAnswers: Boolean) {
    textView.setTextColor(
        ColorStateList.valueOf(
            textView.context.resources.getColor(getColorByState(enoughAnswers))
        )
    )
}

@BindingAdapter("progressBarColor")
fun bindProgressBarColor(progressBar: ProgressBar, enoughPercentage: Boolean) {
    progressBar.progressTintList = ColorStateList.valueOf(
        progressBar.context.resources.getColor(getColorByState(enoughPercentage))
    )
}

private fun getColorByState(goodState: Boolean): Int {
    var color = R.color.green
    if (!goodState) {
        color = R.color.red
    }
    return color
}

@BindingAdapter("onOptionClickListener")
fun bindOnOptionClickListener(textView: TextView, clickListener: OnOptionClickListener) {
    textView.setOnClickListener {
        clickListener.onOptionClick(textView.text.toString().toInt())
    }
}