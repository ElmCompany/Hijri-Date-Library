package sa.elm.android.datepicker

import android.widget.NumberPicker

import java.text.DecimalFormatSymbols
import java.util.Locale

/**
 * https://github.com/drawers/SpinnerDatePicker/blob/master/SpinnerDatePickerLib/src/main/java/com/tsongkha/spinnerdatepicker/TwoDigitFormatter.java
 */
class TwoDigitFormatter : NumberPicker.Formatter {
    private val mBuilder = StringBuilder()

    var mZeroDigit: Char = ' '
    private lateinit var mFmt: java.util.Formatter

    private val mArgs = arrayOfNulls<Any>(1)

    init {
        val locale = Locale.US
        init(locale)
    }

    private fun init(locale: Locale) {
        mFmt = createFormatter(locale)
        mZeroDigit = getZeroDigit(locale)
    }

    override fun format(value: Int): String {
        val currentLocale = Locale.US
        if (mZeroDigit != getZeroDigit(currentLocale)) {
            init(currentLocale)
        }
        mArgs[0] = value
        mBuilder.delete(0, mBuilder.length)
        mFmt.format("%02d", *mArgs)
        return mFmt.toString()
    }

    private fun getZeroDigit(locale: Locale): Char {
        return DecimalFormatSymbols.getInstance(locale).zeroDigit
    }

    private fun createFormatter(locale: Locale): java.util.Formatter {
        return java.util.Formatter(mBuilder, locale)
    }
}