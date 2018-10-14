package sa.elm.android.datepicker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.github.msarhan.ummalqura.calendar.UmmalquraCalendar
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(),DatePickerFragment.DatePickerFragmentListener {


    private var selectedDay: Int? = null
    private var selectedMonth: Int? = null
    private var selectedYear: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            onBirthOfDateClicked()
        }
    }

    override fun onDatePicked(day: Int, month: Int, year: Int) {
        date.text = "$day/$month/$year"
        (supportFragmentManager.findFragmentByTag("DatePickerFragment") as DatePickerFragment).dismiss()
    }

    fun displayHijriDatePicker(maxYear: Int, currentYear: Int, currentMonth: Int, currentDay: Int) {
        val fragmentDatePickerFragment = DatePickerFragment.newInstance(
                DatePickerFragment.DatePickerDto(maxYear, currentYear, currentMonth, currentDay))
        fragmentDatePickerFragment.show(supportFragmentManager, "DatePickerFragment")
    }


    fun onBirthOfDateClicked() {
        var year = selectedYear
        var month = selectedMonth
        var day = selectedDay

        val cal = UmmalquraCalendar()
        val maxYear = cal.get(Calendar.YEAR)

        if (year == null || month == null || day == null) {
            year = cal.get(Calendar.YEAR)
            month = cal.get(Calendar.MONTH) + 1
            day = cal.get(Calendar.DAY_OF_MONTH)
        }

        displayHijriDatePicker(maxYear, year, month, day)
    }

}
