package se.ingenuity.samples.feature.main

import android.app.Activity
import android.os.Bundle
import android.text.TextUtils
import android.text.style.TextAppearanceSpan
import android.widget.TextView
import androidx.core.text.set
import androidx.core.text.toSpannable
import se.ingenuity.samples.R

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity__main)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        val textView = findViewById<TextView>(R.id.spanned_text)

        val spannableText = textView.text.toSpannable()

        var start = TextUtils.indexOf(textView.text, "regular")
        var end = start + "regular".length
        spannableText[start, end] =
            TextAppearanceSpan(this, R.style.TextAppearance_AppTheme_Regular)

        start = TextUtils.indexOf(textView.text, "bold")
        end = start + "bold".length
        spannableText[start, end] =
            TextAppearanceSpan(this, R.style.TextAppearance_AppTheme_Bold)

        start = TextUtils.indexOf(textView.text, "italic")
        end = start + "italic".length
        spannableText[start, end] =
            TextAppearanceSpan(this, R.style.TextAppearance_AppTheme_Italic)

        start = TextUtils.indexOf(textView.text, "bold italic")
        end = start + "bold italic".length
        spannableText[start, end] =
            TextAppearanceSpan(this, R.style.TextAppearance_AppTheme_Bold)
        spannableText[start, end] =
            TextAppearanceSpan(this, R.style.TextAppearance_AppTheme_Italic)
        textView.text = spannableText
    }
}
