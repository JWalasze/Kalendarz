package com.example.projekt_kalendarz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projekt_kalendarz.databinding.DayViewBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList



class DayActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: DayViewBinding
    private lateinit var selectedDate: LocalDate

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = DayViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var myIntent: Intent = intent
        selectedDate = LocalDate.of(
            myIntent.getStringExtra("YEAR_NUMBER")!!.toInt(),
            myIntent.getStringExtra("MONTH_NUMBER")!!.toInt(),
            myIntent.getStringExtra("DAY_NUMBER")!!.toInt())

        binding.monthName.text = monthYearFromDate(selectedDate)
        binding.marginInsideLeft.setOnClickListener(this)
        binding.marginInsideRight.setOnClickListener(this)
        binding.dayBackground1.setOnClickListener(this)
        binding.dayBackground2.setOnClickListener(this)
        binding.dayBackground3.setOnClickListener(this)
        binding.dayBackground4.setOnClickListener(this)

        /*initWidgets()

        val arrayTasks: ArrayList<Task> = ArrayList()
        arrayTasks.add(
            Task("Laryngolog",
            Date(2022, 3, 2, 15, 45),
            Date(2022, 3, 2, 16, 0),
            "Świętego Antoniego",
            "Pamiętać, żeby kupić jajka"))
        arrayTasks.add(
            Task("Spotkanie z Samem",
                Date(2022, 3, 2, 18, 45),
                Date(2022, 3, 2, 20, 0),
                "Wrocławska",
                "Dziś spotkała mnie pewna sytuacja..."))
        arrayTasks.add(
            Task("Spotkanie z Suzzie",
                Date(2022, 3, 2, 18, 45),
                Date(2022, 3, 2, 20, 0),
                "Ukryta",
                "Dziś spotkała mnie pewna sytuacja..."))
        arrayTasks.add(
            Task("Laryngolog",
                Date(2022, 3, 2, 15, 45),
                Date(2022, 3, 2, 16, 0),
                "Świętego Antoniego",
                "Pamiętać, żeby kupić jajka"))

        val calenderDayAdapter = RecyclerDayAdapter(this, arrayTasks, arrayTasks.size)
        recycleViewDay.adapter = calenderDayAdapter
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        recycleViewDay.layoutManager = layoutManager

        val thisIntent = intent
        val day: String? = thisIntent.getStringExtra("DAY_NUMBER")
        val month: String? = thisIntent.getStringExtra("MONTH_NUMBER")
        val year: String? = thisIntent.getStringExtra("YEAR_NUMBER")
        if (day != null && month != null && year != null) {
            setItems(day, month, year)
        }*/
    }

    private fun updateDate()
    {
        binding.monthName.text = monthYearFromDate(selectedDate)
    }

    private fun monthYearFromDate(date: LocalDate): String
    {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("d MMMM")
        return date.format(formatter)
    }

    override fun onClick(view: View)
    {
        when (view)
        {
            binding.marginInsideLeft -> {
                selectedDate = selectedDate.minusDays(1)
                updateDate()
            }
            binding.marginInsideRight -> {
                selectedDate = selectedDate.plusDays(1)
                updateDate()
            }
            binding.dayBackground1 -> {
                Toast.makeText(this, R.string.odpowiedz, Toast.LENGTH_LONG).show()
            }
            binding.dayBackground2 -> {
                setResult("MONTH")
            }
            binding.dayBackground3 -> {
                setResult("YEAR")
            }
        }
    }

    private fun setResult(yearOrMonth: String)
    {
        val myDayIntent = Intent(applicationContext, MainActivity::class.java)

        myDayIntent.putExtra("NEW_YEAR", selectedDate.year)
        myDayIntent.putExtra("NEW_MONTH", selectedDate.monthValue)
        myDayIntent.putExtra("MONTH_OR_YEAR", yearOrMonth)
        setResult(RESULT_OK, myDayIntent)
        finish()
    }

    override fun onSaveInstanceState(outState: Bundle)
    {
        outState.putInt("SELECTED_DATE_YEAR", selectedDate.year)
        outState.putInt("SELECTED_DATE_MONTH", selectedDate.monthValue)
        outState.putInt("SELECTED_DATE_DAY", selectedDate.dayOfMonth)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle)
    {
        val yearsToAdd: Int = selectedDate.year - savedInstanceState.getInt("SELECTED_DATE_YEAR")
        val monthsToAdd: Int = selectedDate.monthValue - savedInstanceState.getInt("SELECTED_DATE_MONTH")
        val daysToAdd: Int = selectedDate.dayOfMonth - savedInstanceState.getInt("SELECTED_DATE_DAY")
        selectedDate = selectedDate.minusYears(yearsToAdd.toLong())
        selectedDate = selectedDate.minusMonths(monthsToAdd.toLong())
        selectedDate = selectedDate.minusDays(daysToAdd.toLong())
        updateDate()
    }


}