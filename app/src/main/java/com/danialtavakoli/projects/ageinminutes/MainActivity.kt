package com.danialtavakoli.projects.ageinminutes

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonSelectDate = findViewById<Button>(R.id.buttonSelectDate)
        val textViewAgeInMinutes = findViewById<TextView>(R.id.textViewAgeInMinutes)
        val textViewSelectDate = findViewById<TextView>(R.id.textViewSelectedDate)
        val textViewAgeStatus = findViewById<TextView>(R.id.textViewAgeStatus)
        buttonSelectDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)
            val datePicker = DatePickerDialog(
                this,
                fun(_: DatePicker, year: Int, month: Int, day: Int) {
                    val selectedDateInString = "${day}/${month + 1}/${year}"
                    val simpleDateFormatter = SimpleDateFormat("dd/mm/yyyy")
                    val selectedDate = simpleDateFormatter.parse(selectedDateInString)
                    val selectedDateInMinutes = selectedDate?.time?.div(60000)
                    val currentDate =
                        simpleDateFormatter.parse(simpleDateFormatter.format(System.currentTimeMillis()))
                    val currentDateInMinutes = currentDate?.time?.div(60000)
                    val ageInMinutes = currentDateInMinutes?.minus(selectedDateInMinutes!!)
                    when (ageInMinutes?.div(525600)?.toInt()) {
                        in 0..3 -> textViewAgeStatus.text = "انرژی و سرزندگی"
                        in 4..6 -> textViewAgeStatus.text = "بازیگوشی"
                        in 7..8 -> textViewAgeStatus.text = "تخیل"
                        in 9..11 -> textViewAgeStatus.text = "ابتکار و خلاقیت"
                        in 12..20 -> textViewAgeStatus.text = "شور و اشتیاق"
                        in 21..35 -> textViewAgeStatus.text = "همت و پشتکار"
                        in 36..50 -> textViewAgeStatus.text = "تعمق و ژرف اندیشی"
                        in 51..80 -> textViewAgeStatus.text = "سخاوت و خیرخواهی"
                        else -> textViewAgeStatus.text = "خردمندی و فضیلت"
                    }
                    textViewSelectDate.text = selectedDateInString
                    textViewAgeInMinutes.text = ageInMinutes.toString()
                },
                year,
                month,
                day
            )
            datePicker.datePicker.maxDate = Date().time
            datePicker.show()
        }
    }
}