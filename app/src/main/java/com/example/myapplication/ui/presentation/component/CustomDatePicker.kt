import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar

@Composable
fun ShowDatePickerDialog(
    context: Context,
    onDateSelected: (day: Int, month: Int, year: Int) -> Unit
) {
    val calendar = Calendar.getInstance()
    val dateSetListener = remember {
        DatePickerDialog.OnDateSetListener { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            onDateSelected(dayOfMonth, month + 1, year) // Month is 0-based
        }
    }

    DatePickerDialog(
        context,
        dateSetListener,
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    ).show()
}
