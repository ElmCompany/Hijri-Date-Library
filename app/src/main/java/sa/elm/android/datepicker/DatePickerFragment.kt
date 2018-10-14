package sa.elm.android.datepicker

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_date_picker.*
import java.io.Serializable

class DatePickerFragment : BottomSheetDialogFragment() {

    private var listener: DatePickerFragmentListener? = null

    class DatePickerDto(val maxYear: Int, val currentYear: Int, val currentMonth: Int, val currentDay: Int) : Serializable

    companion object {
        private const val END_MONTH = 12
        private const val END_DAYS = 30
        private const val DAY_MONTH_START = 1
        private const val START_YEAR = 1300

        private const val EXTRA_DATE_PICKER_INPUT = "EXTRA_DATE_PICKER_INPUT"

        fun newInstance(datePickerDto: DatePickerDto): DatePickerFragment {
            val fragment = DatePickerFragment()
            val bundle = Bundle()
            bundle.putSerializable(EXTRA_DATE_PICKER_INPUT, datePickerDto)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_date_picker, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is DatePickerFragmentListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement DatePickerFragmentListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val datePickerDto = arguments?.get(EXTRA_DATE_PICKER_INPUT) as DatePickerDto

        selectDateButton.setOnClickListener {
            listener?.onDatePicked(dobDay.value, dobMonth.value, dobYear.value)
        }

        dobDay.setFormatter(TwoDigitFormatter())
        dobDay.minValue = DAY_MONTH_START
        dobDay.maxValue = END_DAYS
        dobDay.wrapSelectorWheel = false

        dobDay.value = datePickerDto.currentDay

        dobMonth.setFormatter(TwoDigitFormatter())
        dobMonth.minValue = DAY_MONTH_START
        dobMonth.maxValue = END_MONTH
        dobMonth.wrapSelectorWheel = false

        dobMonth.value = datePickerDto.currentMonth

        dobYear.setFormatter(TwoDigitFormatter())
        dobYear.minValue = START_YEAR
        dobYear.maxValue = datePickerDto.maxYear
        dobYear.wrapSelectorWheel = false

        dobYear.value = datePickerDto.currentYear
    }

    interface DatePickerFragmentListener {
        fun onDatePicked(day: Int, month: Int, year: Int)
    }
}
