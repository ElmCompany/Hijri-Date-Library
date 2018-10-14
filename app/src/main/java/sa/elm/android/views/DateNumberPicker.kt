package sa.elm.android.views

import android.content.Context
import android.util.AttributeSet
import android.widget.NumberPicker
import timber.log.Timber
import java.lang.reflect.Field

class DateNumberPicker(context: Context, attrs: AttributeSet) : NumberPicker(context, attrs) {
    init {
        var numberPickerClass: Class<*>? = null
        var selectionDivider: Field? = null

        try {
            numberPickerClass = Class.forName("android.widget.NumberPicker")
            selectionDivider = numberPickerClass.getDeclaredField("mSelectionDivider")

            selectionDivider.isAccessible = true
            selectionDivider.set(this, null)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }
}