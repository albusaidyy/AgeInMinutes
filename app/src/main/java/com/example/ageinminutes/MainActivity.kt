package com.example.ageinminutes

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var btnDatePicker: Button
    private lateinit var tvSelectedDate: TextView
    private lateinit var tvAgeInMinutes: TextView

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker = findViewById(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)
//            Toast.makeText(this, "Hello Button", Toast.LENGTH_LONG).show()
        }

    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun clickDatePicker(view: View) {
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val dpd = DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    val selectedDate = "$dayOfMonth/${month + 1}/$year"
                    tvSelectedDate = findViewById(R.id.tvSelectedDate)
                    tvSelectedDate.text = selectedDate
                    Toast.makeText(this, "DatePicker Works!", Toast.LENGTH_LONG).show()
                    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                    val theDate = sdf.parse(selectedDate)
                    val selectedDateInMinutes = theDate!!.time / 60000 //in minutes
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis())) //get current date in the format given
                    val currentDateToMinutes = currentDate!!.time / 60000 //convert into minutes
                    val differenceInMinutes = currentDateToMinutes - selectedDateInMinutes //get the difference
                    tvAgeInMinutes = findViewById(R.id.tvSelectedDateInMinutes)
                    tvAgeInMinutes.text = differenceInMinutes.toString()

                },
                year,
                month,
                day
            )
            dpd.datePicker.maxDate = myCalender.timeInMillis //set limit to current date
            dpd.show()
        }


    }


}
