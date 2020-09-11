package se.ingenuity.samples.feature.quiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.tatarka.bindingcollectionadapter2.itemBindingOf
import se.ingenuity.samples.BR
import se.ingenuity.samples.R
import se.ingenuity.samples.feature.databinding.OnClickListener

class QuizViewModel : ViewModel() {
    val currentPosition = MutableLiveData<Int>()

    val quizzes = MutableLiveData<List<SubViewModel>>()

    val pagerBinding = itemBindingOf<SubViewModel> { itemBinding, _, _ ->
        itemBinding.set(BR.subViewModel, R.layout.quiz__container)
    }

    init {
        val clickListener = OnClickListener<Any> {
            val position = currentPosition.value!!
            if (position < quizzes.value!!.lastIndex) {
                currentPosition.value = position + 1
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            delay(1000)

            quizzes.postValue(
                listOf(
                    QuizSingleSubViewModel(viewModelScope, clickListener),
                    QuizMultipleSubViewModel(viewModelScope, clickListener),
                    QuizSingleSubViewModel(viewModelScope, clickListener),
                    QuizMultipleSubViewModel(viewModelScope, clickListener),
                    QuizMultipleSubViewModel(viewModelScope, clickListener),
                )
            )
        }
    }
}