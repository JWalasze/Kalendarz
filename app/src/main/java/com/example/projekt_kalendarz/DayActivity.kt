package com.example.projekt_kalendarz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList



class DayActivity : AppCompatActivity() {

    private lateinit var recycleViewDay: RecyclerView
    private lateinit var monthView: TextView
    private lateinit var dayView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.day_view)

        initWidgets()

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
        }
    }

    private fun initWidgets()
    {
        recycleViewDay = findViewById(R.id.recycleViewDay)
        monthView = findViewById(R.id.monthView)
        dayView = findViewById(R.id.dayView)
    }

    private fun setItems(day: String, month: String, year: String)
    {
        dayView.text = day
        monthView.text = month
    }

    fun moreDetailsTask(view: View)
    {
        dayView.text = "dziala...elo"
    }


}