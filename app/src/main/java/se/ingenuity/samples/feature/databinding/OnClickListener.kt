package se.ingenuity.samples.feature.databinding

fun interface OnClickListener<I> {
    fun onClick(item: I)
}