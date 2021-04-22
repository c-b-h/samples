package se.ingenuity.snippets.extension

import android.view.View
import android.view.ViewGroup
import androidx.core.view.children

inline fun <reified T : View> View.findViewByInstance(): T? {
    return findViewByPredicateTraversal(null) { view -> view is T }
}

fun <T : View> View.findViewByPredicateTraversal(
    childToSkip: View?,
    predicate: (View) -> Boolean,
): T? {
    if (predicate(this)) {
        return this as T
    } else if (this is ViewGroup) {
        children.forEach { v ->
            if (v !== childToSkip /*&& v.mPrivateFlags and PFLAG_IS_ROOT_NAMESPACE === 0*/) {
                val grandChild = v.findViewByPredicateTraversal<T>(null, predicate)
                if (grandChild != null) {
                    return grandChild
                }
            }
        }
    }
    return null
}