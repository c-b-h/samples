package se.ingenuity.samples.feature.quiz

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.tatarka.bindingcollectionadapter2.itemBindingOf
import se.ingenuity.samples.BR
import se.ingenuity.samples.R
import se.ingenuity.samples.feature.databinding.OnClickListener

class QuizSingleSubViewModel(
    viewModelScope: CoroutineScope,
    clickListener: OnClickListener<Any>
) : SubViewModel {
    val items = MutableLiveData<List<CharSequence>>()

    val itemBinding = itemBindingOf<CharSequence> { itemBinding, _, _ ->
        itemBinding.set(BR.buttonLabel, R.layout.item__option_button)
            .bindExtra(BR.clickListener, clickListener)
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000)

            items.postValue(listOf("Single"))
        }
    }

    override val layoutResId: Int
        get() = R.layout.quiz__single
}