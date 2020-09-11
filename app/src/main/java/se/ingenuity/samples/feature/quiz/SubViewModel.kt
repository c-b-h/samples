package se.ingenuity.samples.feature.quiz

import android.view.View
import android.view.ViewStub
import androidx.annotation.LayoutRes
import androidx.core.view.doOnAttach
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewTreeLifecycleOwner
import se.ingenuity.samples.BR

interface SubViewModel : ViewStub.OnInflateListener {
    @get:LayoutRes
    val layoutResId: Int

    override fun onInflate(stub: ViewStub, inflated: View) {
        inflated.doOnAttach { view ->
            DataBindingUtil.getBinding<ViewDataBinding>(view)!!.apply {
                lifecycleOwner = ViewTreeLifecycleOwner.get(root)
                setVariable(BR.subViewModel, this@SubViewModel)
            }
        }
    }
}